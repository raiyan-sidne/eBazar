package net.therap.ebazar.web.controller;

import net.therap.ebazar.Command.UserLoginCMD;
import net.therap.ebazar.domain.User;
import net.therap.ebazar.service.UserService;
import net.therap.ebazar.web.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

import static net.therap.ebazar.Util.Url.*;

/**
 * @author hasin.raiyan
 * @since 3/30/21
 */
@Controller
public class LoginController {

    private static final String LOGIN_COMMAND = "userLogin";
    private static final String COMMAND = "user";

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @InitBinder(LOGIN_COMMAND)
    public void loginCommandInitBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor((true)));
    }

    @InitBinder(COMMAND)
    public void registerCommandInitBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(userValidator);
    }

    @GetMapping({INDEX, "/login"})
    public String indexPage(ModelMap model) {
        model.addAttribute(LOGIN_COMMAND, new UserLoginCMD());

        return INDEX_PAGE;
    }

    @PostMapping(LOGIN)
    public String loggingProcess(@Valid @ModelAttribute(LOGIN_COMMAND) UserLoginCMD user,
                                 BindingResult result,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes,
                                 Locale locale,
                                 ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute(LOGIN_COMMAND, user);

            return INDEX_PAGE;
        }

        HttpSession session = request.getSession();
        if (!userService.userVerification(user)) {
            redirectAttributes.addFlashAttribute(
                    messageSource.getMessage("label.message.var", new Object[]{}, locale),
                    messageSource.getMessage("label.message.invalidCredential", new Object[]{}, locale));

            return REDIRECT + INDEX;
        } else {
            User verifiedUser = userService.getUserByEmail(user.getEmail());
            setSessionValues(verifiedUser, session);
            redirectAttributes.addFlashAttribute(
                    messageSource.getMessage("label.message.var", new Object[]{}, locale),
                    messageSource.getMessage("label.message.welcome", new Object[]{verifiedUser.getName()}, locale));

            return REDIRECT + HOME;
        }
    }

    @GetMapping(REGISTER)
    public String registerPage(ModelMap model) {
        model.addAttribute(COMMAND, new User());

        return REGISTER_PAGE;
    }

    @PostMapping(REGISTER)
    public String registeringProcess(@Valid @ModelAttribute(COMMAND) User user,
                                     BindingResult result,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttributes,
                                     Locale locale,
                                     ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute(COMMAND, user);

            return REGISTER_PAGE;
        }
        user.setPassword(userService.getHashedPassword(user.getPassword()));

        userService.save(user);
        HttpSession session = request.getSession();
        user = userService.getUserByEmail(user.getEmail());
        setSessionValues(user, session);
        redirectAttributes.addFlashAttribute(
                messageSource.getMessage("label.message.var", new Object[]{}, locale),
                messageSource.getMessage("label.message.welcome", new Object[]{user.getName()}, locale));

        return REDIRECT + HOME;
    }

    @GetMapping("/logout")
    public String logOutProcess(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("userId");
        session.removeAttribute("role");
        session.invalidate();

        return REDIRECT + INDEX;
    }

    private void setSessionValues(User user, HttpSession session) {
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
    }
}