package net.therap.ebazar.web.validator;

import net.therap.ebazar.dao.UserProductDao;
import net.therap.ebazar.domain.Product;
import net.therap.ebazar.domain.User;
import net.therap.ebazar.domain.UserProduct;
import net.therap.ebazar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

import static net.therap.ebazar.Util.Status.PENDING;
import static net.therap.ebazar.Util.Status.POSTED;

/**
 * @author hasin.raiyan
 * @since 4/28/21
 */
@Component
public class ProductValidator implements Validator {

    private static final String PATH = "name";
    private static final String ERROR_CODE = "product.name.exists";
    private static final String PRODUCT_EXISTS = "You already have an Unsold product with the same name";

    @Autowired
    private UserProductDao userProductDao;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        User user = getCurrentUser();

        if(sameProductExists(user, product)) {
            errors.rejectValue(PATH, ERROR_CODE, PRODUCT_EXISTS);
        }
    }

    private boolean sameProductExists(User user, Product product) {
        List<UserProduct> userProductList = userProductDao.getUserProductListOfUser(user, POSTED);
        if(existsInList(userProductList, product)) {
            return true;
        }
        userProductList = userProductDao.getUserProductListOfUser(user, PENDING);
        if(existsInList(userProductList, product)) {
            return true;
        }
        return false;
    }

    private boolean existsInList(List<UserProduct> userProductList, Product product) {
        for(UserProduct userProduct : userProductList) {
            if(product.getName().equals(userProduct.getProduct().getName())) {
                return true;
            }
        }
        return false;
    }

    private User getCurrentUser() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession();
        return userService.getUserById((int) session.getAttribute("userId"));
    }
}