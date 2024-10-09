package net.therap.ebazar.service;

import net.therap.ebazar.dao.ChatDao;
import net.therap.ebazar.domain.Chat;
import net.therap.ebazar.domain.ChatPair;
import net.therap.ebazar.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author hasin.raiyan
 * @since 4/15/21
 */
@Service
public class ChatService {

    @Autowired
    private ChatDao chatDao;

    @Transactional
    public Chat save(Chat chat) {
        ChatPair chatPair = chat.getChatPair();
        chatPair = chatDao.saveOrUpdate(chatPair);
        chat.setChatPair(chatPair);
        return chatDao.save(chat);
    }

    public List<ChatPair> getChatPairList(User user) {
        return chatDao.getChatPairList(user);
    }

    public List<Chat> getChatListByChatPair(ChatPair chatPair) {
        return chatDao.getChatListByChatPair(chatPair);
    }

    public ChatPair getChatPairByUsers(User sender, User receiver) {
        ChatPair chatPair = chatDao.getChatPairByUsers(sender, receiver);
        return Objects.isNull(chatPair) ? chatDao.getChatPairByUsers(receiver, sender) : chatPair;
    }
}