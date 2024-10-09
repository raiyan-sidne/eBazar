package net.therap.ebazar.service;

import net.therap.ebazar.dao.BidDao;
import net.therap.ebazar.dao.UserProductDao;
import net.therap.ebazar.domain.Bid;
import net.therap.ebazar.domain.User;
import net.therap.ebazar.domain.UserProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static net.therap.ebazar.Util.Status.OFFERED;

/**
 * @author hasin.raiyan
 * @since 4/14/21
 */
@Service
public class BidService {

    @Autowired
    private BidDao bidDao;

    @Autowired
    private UserProductDao userProductDao;

    @Transactional
    public Bid saveOrUpdate(Bid bid) {
        UserProduct userProduct = userProductDao
                .getUserProductByUserAndProduct(bid.getUser(), bid.getUserProduct().getProduct(),OFFERED);
        if (Objects.isNull(userProduct)) {
            userProduct = new UserProduct();
            userProduct.setProduct(bid.getUserProduct().getProduct());
            userProduct.setUser(bid.getUser());
            userProduct.setStatus(OFFERED);
        }
        userProductDao.saveOrUpdate(userProduct, userProduct.isNew());
        return bidDao.saveOrUpdate(bid, bid.isNew());
    }

    public Bid getBidByUserProductAndUser(UserProduct userProduct, User user) {
        List<Bid> bidList = bidDao.getBidList(user, userProduct);
        return bidList.isEmpty() ? null : bidList.get(0);
    }

    public List<Bid> getBidListByUserProduct(UserProduct userProduct) {
        return bidDao.getBidList(null, userProduct);
    }

    public Bid getBidById(int id) {
        return bidDao.findById(new Bid(), id);
    }

    public List<Bid> getBidByUser(User user) {
        return bidDao.getBidList(user, null);
    }
}