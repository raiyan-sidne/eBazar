package net.therap.ebazar.dao;

import net.therap.ebazar.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hasin.raiyan
 * @since 4/10/21
 */
@Repository
public class ProductDao extends DaoHelper<Product> {

    @Transactional
    public Product saveOrUpdate(Product product) {
        return product.isNew() ? super.save(product) : super.update(product);
    }
}