package net.therap.ebazar.service;

import net.therap.ebazar.dao.ImageFileDao;
import net.therap.ebazar.dao.ProductDao;
import net.therap.ebazar.domain.ImageFile;
import net.therap.ebazar.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hasin.raiyan
 * @since 4/10/21
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ImageFileDao imageFileDao;

    @Transactional
    public Product save(Product product) {
        List<ImageFile> imageFileList = product.getImageFiles();
        List<ImageFile> newImageFileList = new ArrayList<>();
        for (ImageFile imageFile : imageFileList) {
            imageFile = imageFileDao.saveOrUpdate(imageFile);
            newImageFileList.add(imageFile);
        }
        product.setImageFiles(newImageFileList);
        return productDao.saveOrUpdate(product);
    }
}