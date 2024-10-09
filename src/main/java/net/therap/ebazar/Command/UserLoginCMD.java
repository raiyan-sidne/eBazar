package net.therap.ebazar.Command;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hasin.raiyan
 * @since 4/1/21
 */
public class UserLoginCMD implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "{user.email.null.message}")
    private String email;

    @NotNull(message = "{user.password.null.message}")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}