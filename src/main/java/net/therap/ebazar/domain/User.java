package net.therap.ebazar.domain;

import net.therap.ebazar.Util.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static net.therap.ebazar.Util.Role.ADMIN;

/**
 * @author hasin.raiyan
 * @since 4/1/21
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "{user.name.null.message}")
    @Size(min = 3, max = 99)
    private String name;

    @NotNull(message = "{user.email.null.message}")
    private String email;

    @NotNull(message = "{user.password.null.message}")
    @Size(min = 3, max = 100)
    private String password;

    @NotNull(message = "{user.phone.null.message}")
    @Size(min = 11, max = 14)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinTable(
            name = "user_image",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private ImageFile image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ImageFile getImage() {
        return image;
    }

    public void setImage(ImageFile image) {
        this.image = image;
    }

    public boolean isAdmin() {
        return ADMIN.equals(getRole());
    }
}