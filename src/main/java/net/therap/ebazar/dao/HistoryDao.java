package net.therap.ebazar.dao;

import net.therap.ebazar.domain.History;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author hasin.raiyan
 * @since 4/12/21
 */
@Repository
public class HistoryDao extends DaoHelper<History> {

    @PersistenceContext
    private EntityManager em;

    public List<History> getHistory() {
        return em.createQuery("SELECT history FROM History history ORDER BY history.date DESC", History.class)
                .getResultList();
    }
}