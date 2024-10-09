package net.therap.ebazar.web.controller;

import net.therap.ebazar.domain.*;
import net.therap.ebazar.service.BidService;
import net.therap.ebazar.service.UserProductService;
import net.therap.ebazar.service.UserService;
import net.therap.ebazar.web.editor.PriceEditor;
import net.therap.ebazar.web.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static net.therap.ebazar.Util.Status.PENDING;
import static net.therap.ebazar.Util.Url.*;

/**
 * @author hasin.raiyan
 * @since 4/7/21
 */
@Controller
public class ProductController {

    private static final String COMMAND = "product";
    private static final String DIRECTORY = "/images";
    private static final int MAX_LIMIT = 1000000000;

    @Autowired
    private UserProductService userProductService;

    @Autowired
    private UserService userService;

    @Autowired
    private BidService bidService;

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private PriceEditor priceEditor;

    @Autowired
    private MessageSource messageSource;

    @InitBinder(COMMAND)
    public void productInitBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(productValidator);
        binder.registerCustomEditor(double.class, priceEditor);
    }

    @GetMapping(PRODUCT)
    public String productPage(ModelMap model) {
        model.addAttribute(COMMAND, new Product());

        return PRODUCT_PAGE;
    }

    @PostMapping(PRODUCT)
    public String postingProduct(@Valid @ModelAttribute Product product,
                                 BindingResult result,
                                 @RequestParam("files") MultipartFile multipartFiles[],
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes,
                                 Locale locale,
                                 ModelMap model) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute(COMMAND, product);

            return PRODUCT_PAGE;
        }

        product.setImageFiles(getImageFilesAndStoreFiles(multipartFiles, session));
        product.setDate(new Date());
        int userId = (int) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        userProductService.saveProductAndUserProduct(user, product, PENDING);

        redirectAttributes.addFlashAttribute(
                messageSource.getMessage("label.message.var", new Object[]{}, locale),
                messageSource.getMessage("label.product.successful", new Object[]{}, locale));

        return REDIRECT + HOME;
    }

    @GetMapping("/viewProduct")
    public String viewProductPage(@RequestParam(defaultValue = "0") int id,
                                  HttpSession session,
                                  ModelMap model) {

        UserProduct userProduct = userProductService.getUserProductById(id);
        if (Objects.isNull(userProduct)) {
            return REDIRECT + NOTHING_FOUND;
        }
        User user = userService.getUserById((int) session.getAttribute("userId"));
        Bid bid = bidService.getBidByUserProductAndUser(userProduct, user);
        if (Objects.isNull(bid)) {
            bid = new Bid();
            bid.setUserProduct(userProduct);
            bid.setUser(user);
        }
        model.addAttribute("bid", bid);
        if (user.getId() == userProduct.getUser().getId()) {
            model.addAttribute("bidList", bidService.getBidListByUserProduct(userProduct));
            model.addAttribute("ownProduct", true);
        }
        model.addAttribute("user", user);
        model.addAttribute("userProduct", userProduct);

        return "viewProduct";
    }

    @GetMapping("/sellProduct")
    public String dealingProcess(@RequestParam(defaultValue = "0") int id,
                                 RedirectAttributes redirectAttributes,
                                 Locale locale) {

        Bid bid = bidService.getBidById(id);
        if (Objects.nonNull(bid)) {
            userProductService.makeDeal(bid);
            redirectAttributes.addFlashAttribute(
                    messageSource.getMessage("label.message.var", new Object[]{}, locale),
                    messageSource.getMessage("label.successful", new Object[]{}, locale));

            return REDIRECT + HOME;
        } else {
            return REDIRECT + NOTHING_FOUND;
        }
    }

    private List<ImageFile> getImageFilesAndStoreFiles(MultipartFile[] multipartFiles,
                                                       HttpSession session) throws IOException {

        List<ImageFile> imageFiles = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            String path = session.getServletContext().getRealPath(DIRECTORY);
            String fileName = file.getOriginalFilename();
            if (!isImageType(fileName)) {
                continue;
            }
            fileName = generateFileName(fileName);
            writeFile(file, fileName, path);

            ImageFile imageFile = new ImageFile();
            imageFile.setPath(fileName);
            imageFiles.add(imageFile);
        }
        return imageFiles;
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
        BufferedOutputStream bout = new BufferedOutputStream(
                new FileOutputStream(path + File.separator + fileName));
        bout.write(bytes);
        bout.flush();
        bout.close();
    }
}