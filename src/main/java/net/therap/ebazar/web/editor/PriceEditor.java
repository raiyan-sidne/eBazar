package net.therap.ebazar.web.editor;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author hasin.raiyan
 * @since 4/28/21
 */
@Component
public class PriceEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String price) throws IllegalArgumentException {
        if (Objects.isNull(price) || price.isEmpty()) {
            setValue(0.0);
        } else {
            setValue(Double.parseDouble(price));
        }
    }
}