package net.therap.ebazar.web.controller;

import net.therap.ebazar.domain.Chat;
import net.therap.ebazar.domain.ChatPair;
import net.therap.ebazar.domain.User;
import net.therap.ebazar.service.ChatService;
import net.therap.ebazar.service.UserService;
import net.therap.ebazar.web.editor.ChatPairEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

import static net.therap.ebazar.Util.Url.*;

/**
 * @author hasin.raiyan
 * @since 4/17/21
 */
@Controller
public class ChatController {

    private static final String COMMAND = "chat";

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatPairEditor chatPairEditor;

    @InitBinder(COMMAND)
    public void chatInitBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(ChatPair.class, chatPairEditor);
    }

    @GetMapping("/sendMessage")
    public String messagePage(@RequestParam(defaultValue = "0") int id,
                              HttpSession session,
                              ModelMap model) {

        User receiver = userService.getUserById(id);
        if(Objects.isNull(receiver)) {
            return REDIRECT + NOTHING_FOUND;
        }
        User sender = userService.getUserById((int) session.getAttribute("userId"));
        ChatPair chatPair = chatService.getChatPairByUsers(sender, receiver);
        if(Objects.isNull(chatPair)) {
            chatPair = new ChatPair();
            chatPair.setFrom(sender);
            chatPair.setTo(receiver);
        }
        Chat chat = new Chat();
        chat.setChatPair(chatPair);
        model.addAttribute("chat", chat);
        model.addAttribute("chatList", (chatPair.getId() == 0) ? null : chatService.getChatListByChatPair(chatPair));

        return MESSAGE_PAGE;
    }

    @PostMapping("/sendMessage")
    public String sendingMessage(@Valid @ModelAttribute(COMMAND) Chat chat,
                                 BindingResult result,
                                 HttpSession session,
                                 ModelMap model) {

        User user1 = userService.getUserById(chat.getChatPair().getFrom().getId());
        User user2 = userService.getUserById(chat.getChatPair().getTo().getId());
        ChatPair chatPair = chatService.getChatPairByUsers(user1, user2);
        if(Objects.isNull(chatPair)) {
            chatPair = new ChatPair();
            chatPair.setFrom(user1);
            chatPair.setTo(user2);
        }
        chat.setChatPair(chatPair);
        chat.setSender(userService.getUserById((int) session.getAttribute("userId")));

        if (result.hasErrors()) {
            model.addAttribute("chat", chat);
            model.addAttribute("chatList", (chatPair.getId() == 0) ? null : chatService.getChatListByChatPair(chatPair));

            return MESSAGE_PAGE;
        }
        chat.setDate(new Date());
        chatService.save(chat);

        return REDIRECT + "/sendMessage?id=" + getId(chat, chatPair);
    }

    private int getId(Chat chat, ChatPair chatPair) {
        return (chatPair.getFrom().getId() == chat.getSender().getId())
                ? chatPair.getTo().getId() : chatPair.getFrom().getId();
    }
}