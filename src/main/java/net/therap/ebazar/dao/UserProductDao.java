package net.therap.ebazar.dao;

import net.therap.ebazar.Util.Status;
import net.therap.ebazar.domain.Product;
import net.therap.ebazar.domain.User;
import net.therap.ebazar.domain.UserProduct;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author hasin.raiyan
 * @since 4/6/21
 */
@Repository
public class UserProductDao extends DaoHelper<UserProduct> {

    @PersistenceContext
    private EntityManager em;

    public List<UserProduct> getUserProductList(Status status) {
        return em.createQuery("SELECT prod FROM UserProduct prod WHERE prod.status=:status", UserProduct.class)
                .setParameter("status", status)
                .getResultList();
    }

    public UserProduct getUserProductById(int id) {
        List<UserProduct> userProductList =
                em.createQuery("SELECT prod FROM UserProduct prod WHERE prod.id=:id", UserProduct.class)
                        .setParameter("id", id)
                        .getResultList();

        return userProductList.isEmpty() ? null : userProductList.get(0);
    }

    public List<UserProduct> getUserProductListOfUser(User user, Status status) {
        return em.createQuery("SELECT p FROM UserProduct p WHERE p.user=:user AND p.status=:status", UserProduct.class)
                .setParameter("user", user)
                .setParameter("status", status)
                .getResultList();
    }

    public UserProduct getUserProductByUserAndProduct(User user, Product product, Status status) {
        List<UserProduct> userProductList =
                em.createQuery("SELECT p FROM UserProduct p WHERE p.user=:user AND p.product=:product AND p.status=:status", UserProduct.class)
                        .setParameter("user", user)
                        .setParameter("product", product)
                        .setParameter("status", status)
                        .getResultList();

        return userProductList.isEmpty() ? null : userProductList.get(0);
    }

    public UserProduct getUserProductByProduct(Product product, Status status) {
        List<UserProduct> userProductList =
                em.createQuery("SELECT p FROM UserProduct p WHERE p.product=:product AND p.status=:status", UserProduct.class)
                        .setParameter("product", product)
                        .setParameter("status", status)
                        .getResultList();

        return userProductList.isEmpty() ? null : userProductList.get(0);
    }
}