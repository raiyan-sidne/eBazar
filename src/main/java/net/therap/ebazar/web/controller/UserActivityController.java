package net.therap.ebazar.web.controller;

import net.therap.ebazar.Util.Status;
import net.therap.ebazar.domain.Bid;
import net.therap.ebazar.domain.ImageFile;
import net.therap.ebazar.domain.User;
import net.therap.ebazar.domain.UserProduct;
import net.therap.ebazar.service.BidService;
import net.therap.ebazar.service.UserProductService;
import net.therap.ebazar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static net.therap.ebazar.Util.Status.*;
import static net.therap.ebazar.Util.Url.*;

/**
 * @author hasin.raiyan
 * @since 4/12/21
 */
@Controller
public class UserActivityController {

    private static final String COMMAND = "user";
    private static final String DIRECTORY = "/images";
    private static final String ERROR_MESSAGE = "No image uploaded";
    private static final String COMMAND_LIST = "userProductList";
    private static final int MAX_LIMIT = 1000000000;

    @Autowired
    private UserProductService userProductService;

    @Autowired
    private UserService userService;

    @Autowired
    private BidService bidService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping({"/viewSeller", "/viewBuyer", "/viewAdmin"})
    public String viewSeller(@RequestParam(defaultValue = "0") int id,
                             ModelMap model,
                             HttpSession session) {

        User user = userService.getUserById(id);
        User activeUser = userService.getUserById((int) session.getAttribute("userId"));
        if(Objects.isNull(user)) {
            return REDIRECT + NOTHING_FOUND;
        }
        model.addAttribute(COMMAND, user);
        model.addAttribute("ownProfile", user.getId() == activeUser.getId());
        model.addAttribute("viewOwnListsEligibility", user.getId() == activeUser.getId() && !user.isAdmin());

        return PROFILE_PAGE;
    }

    @GetMapping(UPDATE_PROFILE_PIC)
    public String updateProfilePicPage() {
        return UPDATE_PROFILE_PAGE;
    }

    @PostMapping(UPDATE_PROFILE_PIC)
    public String updateProfilePicProcess(@RequestParam("profilePic") MultipartFile multipartFile,
                                          HttpSession session,
                                          RedirectAttributes redirectAttributes,
                                          Locale locale,
                                          ModelMap model) throws IOException {

        if (multipartFile.isEmpty() || !isImageType(multipartFile.getOriginalFilename())) {
            model.addAttribute("message", ERROR_MESSAGE);

            return UPDATE_PROFILE_PAGE;
        }
        User user = userService.getUserById((Integer) session.getAttribute("userId"));
        ImageFile imageFile = Objects.isNull(user.getImage()) ? new ImageFile() : user.getImage();
        user.setImage(getImageFileAndStoreFile(multipartFile, session));
        userService.saveOrUpdate(user, imageFile);
        redirectAttributes.addFlashAttribute(
                messageSource.getMessage("label.message.var", new Object[]{}, locale),
                messageSource.getMessage("label.successful", new Object[]{}, locale));

        return REDIRECT + PROFILE;
    }

    @GetMapping({POSTED_PRODUCT, SOLD_PRODUCT, OFFERED_PRODUCT, BOUGHT_PRODUCT})
    public String viewPostedProduct(ModelMap model,
                                    HttpServletRequest request,
                                    HttpSession session) {

        String action = request.getServletPath();
        Status status = null;
        switch (action) {
            case POSTED_PRODUCT:
                status = POSTED;
                break;
            case SOLD_PRODUCT:
                status = SOLD;
                break;
            case OFFERED_PRODUCT:
                status = OFFERED;
                break;
            case BOUGHT_PRODUCT:
                status = BOUGHT;
                break;
        }
        int userId = (int) session.getAttribute("userId");
        if (status.equals(OFFERED)) {
            model.addAttribute(COMMAND_LIST, getOfferedUserProductList(userId));
        } else if (status.equals(BOUGHT)) {
            model.addAttribute(COMMAND_LIST, getBoughtUserProductList(userId));
        } else {
            model.addAttribute(COMMAND_LIST, userProductService.getUserProductListOfUser(userId, status));
        }

        return USER_PRODUCT_LIST;
    }

    private List<UserProduct> getOfferedUserProductList(int userId) {
        List<Bid> bids = bidService.getBidByUser(userService.getUserById(userId));
        List<UserProduct> userProducts = new ArrayList<>();
        for (Bid bid : bids) {
            UserProduct userProduct = bid.getUserProduct();
            userProducts.add(userProduct);
        }
        return userProducts;
    }

    private List<UserProduct> getBoughtUserProductList(int userId) {
        List<UserProduct> boughtProducts = userProductService.getUserProductListOfUser(userId, BOUGHT);
        List<UserProduct> soldProducts = new ArrayList<>();
        for (UserProduct userProduct : boughtProducts) {
            UserProduct tempUserProduct = userProductService.getUserProductByProduct(userProduct.getProduct(), SOLD);
            soldProducts.add(tempUserProduct);
        }
        return soldProducts;
    }

    private ImageFile getImageFileAndStoreFile(MultipartFile multipartFile,
                                               HttpSession session) throws IOException {

        ImageFile imageFile = new ImageFile();
        String path = session.getServletContext().getRealPath(DIRECTORY);
        String fileName = multipartFile.getOriginalFilename();
        if (!isImageType(fileName)) {
            return imageFile;
        }
        fileName = generateFileName(fileName);
        writeFile(multipartFile, fileName, path);
        imageFile.setPath(fileName);

        return imageFile;
    }

    private boolean isImageType(String filename) {
        String regEx = "[^\\s]+(.*?)\\.(jpg|jpeg|png|gif|JPG|JPEG|PNG|GIF)$";
        return Pattern.compile(regEx).matcher(filename).matches();
    }

    private String generateFileName(String fileName) {
        long time = new Date().getTime();
        fileName = time + "_" + new Random().nextInt(MAX_LIMIT) + "_" + fileName;

        return fileName;
    }

    private void writeFile(MultipartFile file, String fileName, String path) throws IOException {
        byte bytes[] = file.getBytes();
        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        bout.write(bytes);
        bout.flush();
        bout.close();
    }
}