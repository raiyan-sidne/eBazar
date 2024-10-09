package net.therap.ebazar.web.controller;

import net.therap.ebazar.Util.Status;
import net.therap.ebazar.domain.History;
import net.therap.ebazar.domain.User;
import net.therap.ebazar.domain.UserProduct;
import net.therap.ebazar.service.HistoryService;
import net.therap.ebazar.service.UserProductService;
import net.therap.ebazar.service.UserService;
import net.therap.ebazar.web.validator.HistoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static net.therap.ebazar.Util.Status.*;
import static net.therap.ebazar.Util.Url.*;

/**
 * @author hasin.raiyan
 * @since 4/11/21
 */
@Controller
public class AdminController {

    private static final String COMMAND = "history";

    @Autowired
    private UserProductService userProductService;

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private HistoryValidator historyValidator;

    @Autowired
    private MessageSource messageSource;

    @InitBinder(COMMAND)
    public void productInitBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(historyValidator);
    }

    @GetMapping(HISTORY)
    public String historyPage(ModelMap model) {
        model.addAttribute("historyList", historyService.getHistory());

        return HISTORY_PAGE;
    }

    @GetMapping("/approvePost")
    public String approveProcess(@RequestParam(defaultValue = "0") int id,
                                 RedirectAttributes redirectAttributes,
                                 Locale locale,
                                 HttpSession session) {

        UserProduct userProduct = userProductService.getUserProductById(id);
        if (Objects.nonNull(userProduct)) {
            userProduct.setStatus(POSTED);
            historyService.save(createHistory(new History(), userProduct, APPROVED, session));
        } else {
            return REDIRECT + NOTHING_FOUND;
        }

        redirectAttributes.addFlashAttribute(
                messageSource.getMessage("label.message.var", new Object[]{}, locale),
                messageSource.getMessage("label.successful", new Object[]{}, locale));

        return REDIRECT + PENDING_URL;
    }

    @GetMapping("/rejectPost")
    public String rejectPage(@RequestParam(defaultValue = "0") int id,
                             ModelMap model) {

        UserProduct userProduct = userProductService.getUserProductById(id);
        if (Objects.nonNull(userProduct)) {
            History history = new History();
            history.setUserProduct(userProduct);
            model.addAttribute(COMMAND, history);

            return REJECT_POST_PAGE;
        } else {
            return REDIRECT + NOTHING_FOUND;
        }
    }

    @PostMapping("/rejectPost")
    public String rejectionProcess(@Valid @ModelAttribute(COMMAND) History history,
                                   BindingResult result,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes,
                                   Locale locale,
                                   ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute(COMMAND, history);

            return REJECT_POST_PAGE;
        }
        UserProduct userProduct = userProductService.getUserProductById(history.getUserProduct().getId());
        userProduct.setStatus(REJECTED);
        history = createHistory(history, userProduct, REJECTED, session);
        historyService.save(history);
        redirectAttributes.addFlashAttribute(
                messageSource.getMessage("label.message.var", new Object[]{}, locale),
                messageSource.getMessage("label.successful", new Object[]{}, locale));

        return REDIRECT + PENDING_URL;
    }

    private History createHistory(History history,
                                  UserProduct userProduct,
                                  Status status,
                                  HttpSession session) {

        User user = userService.getUserById((int) session.getAttribute("userId"));
        history.setUser(user);
        history.setUserProduct(userProduct);
        history.setStatus(status);
        history.setDate(new Date());

        return history;
    }
}