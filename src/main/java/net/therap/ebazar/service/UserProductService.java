package net.therap.ebazar.service;

import net.therap.ebazar.Util.Status;
import net.therap.ebazar.dao.UserProductDao;
import net.therap.ebazar.domain.Bid;
import net.therap.ebazar.domain.Product;
import net.therap.ebazar.domain.User;
import net.therap.ebazar.domain.UserProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.therap.ebazar.Util.Status.BOUGHT;
import static net.therap.ebazar.Util.Status.SOLD;

/**
 * @author hasin.raiyan
 * @since 4/6/21
 */
@Service
public class UserProductService {

    @Autowired
    private UserProductDao userProductDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveProductAndUserProduct(User user, Product product, Status status) {
        product = productService.save(product);
        UserProduct userProduct = new UserProduct();
        userProduct.setUser(user);
        userProduct.setProduct(product);
        userProduct.setStatus(status);
        userProductDao.saveOrUpdate(userProduct, userProduct.isNew());
    }

    @Transactional
    public void makeDeal(Bid bid) {
        UserProduct userProduct = bid.getUserProduct();
        userProduct.setStatus(SOLD);
        userProductDao.update(userProduct);

        UserProduct newUserProduct = new UserProduct();
        newUserProduct.setProduct(bid.getUserProduct().getProduct());
        newUserProduct.setUser(bid.getUser());
        newUserProduct.setStatus(BOUGHT);
        userProductDao.saveOrUpdate(newUserProduct, userProduct.isNew());
    }

    public UserProduct getUserProductById(int id) {
        return userProductDao.getUserProductById(id);
    }

    public List<UserProduct> getUserProductList(Status status) {
        return userProductDao.getUserProductList(status);
    }

    public List<UserProduct> getUserProductListOfUser(int userId, Status status) {
        User user = userService.getUserById(userId);
        return userProductDao.getUserProductListOfUser(user, status);
    }

    public UserProduct getUserProductByProduct(Product product, Status status) {
        return userProductDao.getUserProductByProduct(product, status);
    }
}