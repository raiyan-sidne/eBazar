package net.therap.ebazar.service;

import net.therap.ebazar.dao.HistoryDao;
import net.therap.ebazar.dao.UserProductDao;
import net.therap.ebazar.domain.History;
import net.therap.ebazar.domain.UserProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hasin.raiyan
 * @since 4/12/21
 */
@Service
public class HistoryService {

    @Autowired
    private HistoryDao historyDao;

    @Autowired
    private UserProductDao userProductDao;

    @Transactional
    public void save(History history) {
        UserProduct userProduct = userProductDao.update(history.getUserProduct());
        history.setUserProduct(userProduct);
        historyDao.save(history);
    }

    public List<History> getHistory() {
        return historyDao.getHistory();
    }
}