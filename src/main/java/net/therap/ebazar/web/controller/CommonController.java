package net.therap.ebazar.web.controller;

import net.therap.ebazar.domain.User;
import net.therap.ebazar.service.ChatService;
import net.therap.ebazar.service.UserProductService;
import net.therap.ebazar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static net.therap.ebazar.Util.Role.ADMIN;
import static net.therap.ebazar.Util.Role.USER;
import static net.therap.ebazar.Util.Status.*;
import static net.therap.ebazar.Util.Url.*;

/**
 * @author hasin.raiyan
 * @since 4/4/21
 */
@Controller
public class CommonController {

    @Autowired
    private UserProductService userProductService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @GetMapping(HOME)
    public String homePage(ModelMap model,
                           HttpSession session) {

        model.addAttribute("userProductList", userProductService.getUserProductList(POSTED));
        model.addAttribute("user", userService.getUserById((int)session.getAttribute("userId")));

        return HOME_PAGE;
    }

    @GetMapping(PROFILE)
    public String profilePage(ModelMap model,
                              HttpSession session) {

        int userId = (int) session.getAttribute("userId");
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("ownProfile", true);
        model.addAttribute("viewOwnListsEligibility", session.getAttribute("role").equals(USER));

        return PROFILE_PAGE;
    }

    @GetMapping(INBOX)
    public String inboxPage(ModelMap model,
                            HttpSession session) {

        User user = userService.getUserById((int) session.getAttribute("userId"));
        model.addAttribute("chatPairList", chatService.getChatPairList(user));

        return INBOX_PAGE;
    }

    @GetMapping(PENDING_URL)
    public String pendingPage(ModelMap model,
                              HttpSession session) {

        User user = userService.getUserById((int)session.getAttribute("userId"));
        if (session.getAttribute("role").equals(ADMIN)) {
            model.addAttribute("userProductList", userProductService.getUserProductList(PENDING));
        } else {
            int userId = (int) session.getAttribute("userId");
            model.addAttribute("userProductList", userProductService.getUserProductListOfUser(userId, PENDING));
        }
        model.addAttribute("user", user);

        return PENDING_PAGE;
    }

    @GetMapping(NOTHING_FOUND)
    public String nothingFoundPage() {
        return NOTHING_FOUND_PAGE;
    }
}