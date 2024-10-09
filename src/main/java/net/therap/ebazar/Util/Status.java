package net.therap.ebazar.Util;

import java.io.Serializable;

/**
 * @author hasin.raiyan
 * @since 4/25/21
 */
public enum Status implements Serializable {

    POSTED("posted"),
    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected"),
    OFFERED("offered"),
    SOLD("sold"),
    BOUGHT("bought");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}