package net.therap.ebazar.service;

import net.therap.ebazar.Command.UserLoginCMD;
import net.therap.ebazar.dao.ImageFileDao;
import net.therap.ebazar.dao.UserDao;
import net.therap.ebazar.domain.ImageFile;
import net.therap.ebazar.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static net.therap.ebazar.Util.Role.USER;

/**
 * @author hasin.raiyan
 * @since 4/4/21
 */
@Service
public class UserService {

    private static final int MAX_LENGTH = 60;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ImageFileDao imageFileDao;

    @Transactional
    public void save(User user) {
        user.setRole(USER);
        userDao.save(user);
    }

    @Transactional
    public void saveOrUpdate(User user, ImageFile imageFile) {
        imageFile.setPath(user.getImage().getPath());
        imageFile = imageFileDao.saveOrUpdate(imageFile);
        user.setImage(imageFile);
        userDao.update(user);
    }

    public User getUserById(int id) {
        return userDao.findById(new User(), id);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public boolean userVerification(UserLoginCMD user) {
        User verifiedUser = getUserByEmail(user.getEmail());
        if (Objects.isNull(verifiedUser)) {
            return false;
        }
        String password = getHashedPassword(user.getPassword());
        return (user.getEmail().equals(verifiedUser.getEmail())
                && password.equals(verifiedUser.getPassword()));
    }

    public String getHashedPassword(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger bigInteger = new BigInteger(1, messageDigest);
            String hashText = bigInteger.toString(16);
            while (hashText.length() < MAX_LENGTH) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}