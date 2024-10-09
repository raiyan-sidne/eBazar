package net.therap.ebazar.dao;

import net.therap.ebazar.domain.ImageFile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hasin.raiyan
 * @since 4/15/21
 */
@Repository
public class ImageFileDao extends DaoHelper<ImageFile> {

    @Transactional
    public ImageFile saveOrUpdate(ImageFile imageFile) {
        return imageFile.isNew() ? super.save(imageFile) : super.update(imageFile);
    }
}