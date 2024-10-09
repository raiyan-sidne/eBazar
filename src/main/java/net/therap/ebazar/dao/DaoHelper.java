package net.therap.ebazar.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author hasin.raiyan
 * @since 4/4/21
 */
@Repository
public class DaoHelper<T> {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public T save(T type) {
        em.persist(type);
        return type;
    }

    @Transactional
    public T update(T type) {
        return em.merge(type);
    }

    @Transactional
    public T saveOrUpdate(T type, boolean isNew) {
        return isNew ? save(type) : update(type);
    }

    @Transactional
    public void remove(T type) {
        em.remove(type);
    }

    public T findById(T type, int id) {
        return (T) em.find(type.getClass(), id);
    }

    public List<?> findAll(T type) {
        return em.createQuery("SELECT t FROM " + type.getClass().getSimpleName() + " t", type.getClass())
                .getResultList();
    }
}