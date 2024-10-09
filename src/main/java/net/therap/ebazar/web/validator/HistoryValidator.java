package net.therap.ebazar.web.validator;

import net.therap.ebazar.domain.History;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * @author hasin.raiyan
 * @since 4/12/21
 */
@Component
public class HistoryValidator implements Validator {

    private static final String NOT_NULL = "You must provide a reason";
    private static final String PATH = "text";
    private static final String ERROR_CODE = "product.price.empty";

    @Override
    public boolean supports(Class<?> clazz) {
        return History.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        History history = (History) target;
        if (Objects.isNull(history.getText()) || history.getText().equals("")) {
            ValidationUtils.rejectIfEmpty(errors, PATH, ERROR_CODE, NOT_NULL);
        }
    }
}