package net.therap.ebazar.domain;

import net.therap.ebazar.Util.Status;

import javax.persistence.*;
import java.io.Serializable;

import static net.therap.ebazar.Util.Status.*;

/**
 * @author hasin.raiyan
 * @since 4/6/21
 */
@Entity
@Table(name = "user_product")
public class UserProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private Status status;

    public boolean isNew() {
        return this.id == 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isSold() {
        return SOLD.equals(getStatus());
    }

    public boolean isPending() {
        return PENDING.equals(getStatus());
    }

    public boolean isPosted() {
        return POSTED.equals(getStatus());
    }
}