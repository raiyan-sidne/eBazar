package net.therap.ebazar.web.editor;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author hasin.raiyan
 * @since 4/17/21
 */
@Component
public class ChatPairEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (Objects.isNull(id) || id.isEmpty()) {
            setValue(null);
        }
    }
}