package net.therap.ebazar.dao;

import net.therap.ebazar.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author hasin.raiyan
 * @since 4/4/21
 */
@Repository
public class UserDao extends DaoHelper<User> {

    @PersistenceContext
    private EntityManager em;

    public User getUserByEmail(String email) {
        List<User> users = em.createQuery("SELECT user FROM User user WHERE user.email = :email", User.class)
                        .setParameter("email", email)
                        .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }

    public User getUserByPhone(String phone) {
        List<User> users = em.createQuery("SELECT user FROM User user WHERE user.phone = :phone", User.class)
                .setParameter("phone", phone)
                .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }
}