package net.therap.ebazar.web.controller;

import net.therap.ebazar.domain.Bid;
import net.therap.ebazar.domain.User;
import net.therap.ebazar.domain.UserProduct;
import net.therap.ebazar.service.BidService;
import net.therap.ebazar.service.UserProductService;
import net.therap.ebazar.service.UserService;
import net.therap.ebazar.web.editor.PriceEditor;
import net.therap.ebazar.web.validator.BidValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

import static net.therap.ebazar.Util.Url.REDIRECT;

/**
 * @author hasin.raiyan
 * @since 4/14/21
 */
@Controller
public class BidController {

    private static final String COMMAND = "bid";

    @Autowired
    private BidService bidService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProductService userProductService;

    @Autowired
    private BidValidator bidValidator;

    @Autowired
    private PriceEditor priceEditor;

    @Autowired
    private MessageSource messageSource;

    @InitBinder(COMMAND)
    public void productInitBinder(WebDataBinder binder) {
        binder.addValidators(bidValidator);
        binder.registerCustomEditor(double.class, priceEditor);
    }

    @PostMapping("/offer")
    public String placeOffer(@Valid @ModelAttribute Bid bid,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Locale locale,
                             ModelMap model) {

        bid = getBid(bid);
        if(result.hasErrors()) {
            model.addAttribute("bid", bid);
            model.addAttribute("userProduct", bid.getUserProduct());

            return "viewProduct";
        }
        bidService.saveOrUpdate(bid);
        redirectAttributes.addFlashAttribute(
                messageSource.getMessage("label.message.var", new Object[]{}, locale),
                messageSource.getMessage("label.successful", new Object[]{}, locale));

        return REDIRECT + "/viewProduct?id=" + bid.getUserProduct().getId();
    }

    private Bid getBid(Bid bid) {
        UserProduct userProduct = userProductService.getUserProductById(bid.getUserProduct().getId());
        User user = userService.getUserById(bid.getUser().getId());
        bid.setUserProduct(userProduct);
        bid.setUser(user);

        return bid;
    }
}