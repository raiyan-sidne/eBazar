package net.therap.ebazar.domain;

import net.therap.ebazar.Util.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hasin.raiyan
 * @since 4/12/21
 */
@Entity
@Table(name = "history")
public class History implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_product_id")
    private UserProduct userProduct;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

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

    public UserProduct getUserProduct() {
        return userProduct;
    }

    public void setUserProduct(UserProduct userProduct) {
        this.userProduct = userProduct;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}