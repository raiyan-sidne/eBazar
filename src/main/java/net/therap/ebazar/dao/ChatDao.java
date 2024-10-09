package net.therap.ebazar.dao;

import net.therap.ebazar.domain.Chat;
import net.therap.ebazar.domain.ChatPair;
import net.therap.ebazar.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author hasin.raiyan
 * @since 4/15/21
 */
@Repository
public class ChatDao extends DaoHelper<Chat> {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public ChatPair saveOrUpdate(ChatPair chatPair) {
        if (chatPair.isNew()) {
            em.persist(chatPair);
            return chatPair;
        } else {
            return em.merge(chatPair);
        }
    }

    public List<ChatPair> getChatPairList(User user) {
        return em.createQuery("SELECT cp FROM ChatPair cp WHERE cp.from=:user OR cp.to=:user", ChatPair.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<Chat> getChatListByChatPair(ChatPair chatPair) {
        return em.createQuery("SELECT chat FROM Chat chat WHERE chat.chatPair=:chatPair ORDER BY chat.date DESC", Chat.class)
                .setParameter("chatPair", chatPair)
                .getResultList();
    }

    public ChatPair getChatPairByUsers(User sender, User receiver) {
        List<ChatPair> chatPairList =
                em.createQuery("SELECT cp FROM ChatPair cp WHERE cp.from=:sender AND cp.to=:receiver", ChatPair.class)
                .setParameter("sender", sender)
                .setParameter("receiver", receiver)
                .getResultList();

        return chatPairList.isEmpty() ? null : chatPairList.get(0);
    }
}