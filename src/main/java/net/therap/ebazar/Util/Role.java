package net.therap.ebazar.Util;

import java.io.Serializable;

/**
 * @author hasin.raiyan
 * @since 4/27/21
 */
public enum Role implements Serializable {

    USER("user"),
    ADMIN("admin");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}