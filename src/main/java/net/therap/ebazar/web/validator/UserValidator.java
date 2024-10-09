package net.therap.ebazar.web.validator;

import net.therap.ebazar.dao.UserDao;
import net.therap.ebazar.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author hasin.raiyan
 * @since 4/4/21
 */
@Component
public class UserValidator implements Validator {

    private static final String EMAIL_EXISTS = "This Email already exists";
    private static final String PHONE_EXISTS = "This Phone Number already exists";
    private static final String EMAIL_INVALID = "This Email is invalid";
    private static final String PHONE_INVALID = "This is not a valid Phone number";
    private static final String EMAIL_PATH = "email";
    private static final String PHONE_PATH = "phone";
    private static final String PHONE_EXISTS_ERROR_CODE = "user.phone.exist";
    private static final String EMAIL_EXISTS_ERROR_CODE = "user.email.exist";
    private static final String EMAIL_INVALID_ERROR_CODE = "user.email.invalid";
    private static final String PHONE_INVALID_ERROR_CODE = "user.phone.invalid";

    @Autowired
    private UserDao userDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (Objects.nonNull(user.getEmail())) {
            if (validEmail(user.getEmail())) {
                if (emailAlreadyExists(user.getEmail())) {
                    errors.rejectValue(EMAIL_PATH, EMAIL_EXISTS_ERROR_CODE, EMAIL_EXISTS);
                }
            } else {
                errors.rejectValue(EMAIL_PATH, EMAIL_INVALID_ERROR_CODE, EMAIL_INVALID);
            }
        }
        if (Objects.nonNull(user.getPhone())) {
            if(validPhone(user.getPhone())) {
                if (phoneAlreadyExists(user.getPhone())) {
                    errors.rejectValue(PHONE_PATH, PHONE_EXISTS_ERROR_CODE, PHONE_EXISTS);
                }
            }
            else {
                errors.rejectValue(PHONE_PATH, PHONE_INVALID_ERROR_CODE, PHONE_INVALID);
            }
        }
    }

    private boolean validEmail(String email) {
        String regEx = "^(.+)@(.+)$";
        return Pattern.compile(regEx).matcher(email).matches();
    }

    private boolean emailAlreadyExists(String email) {
        return Objects.nonNull(userDao.getUserByEmail(email));
    }

    private boolean phoneAlreadyExists(String phone) {
        return Objects.nonNull(userDao.getUserByPhone(phone));
    }

    private boolean validPhone(String phone) {
        String regEx = "^(?:\\+88|88)?(01[0-9]\\d{8})$";
        return Pattern.compile(regEx).matcher(phone).matches();
    }
}