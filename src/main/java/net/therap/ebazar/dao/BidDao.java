package net.therap.ebazar.dao;

import net.therap.ebazar.domain.Bid;
import net.therap.ebazar.domain.User;
import net.therap.ebazar.domain.UserProduct;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

/**
 * @author hasin.raiyan
 * @since 4/14/21
 */
@Repository
public class BidDao extends DaoHelper<Bid> {

    @PersistenceContext
    private EntityManager em;

    public List<Bid> getBidList(User user, UserProduct userProduct) {
        String query = "SELECT bid FROM Bid bid";
        if(Objects.nonNull(user) || Objects.nonNull(userProduct)) {
            query += " WHERE ";
            if(Objects.nonNull(user)) {
                query += "bid.user=:user";
                if(Objects.nonNull(userProduct)) {
                    query += " AND ";
                }
            }
            if(Objects.nonNull(userProduct)) {
                query += "bid.userProduct=:userProduct";
            }
        }
        TypedQuery<Bid> typedQuery = em.createQuery(query, Bid.class);
        if(Objects.nonNull(user)) {
            typedQuery.setParameter("user",user);
        }
        if(Objects.nonNull(userProduct)) {
            typedQuery.setParameter("userProduct",userProduct);
        }

        return typedQuery.getResultList();
    }
}