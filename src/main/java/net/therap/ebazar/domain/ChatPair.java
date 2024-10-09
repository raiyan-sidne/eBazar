package net.therap.ebazar.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author hasin.raiyan
 * @since 4/14/21
 */
@Entity
@Table(name = "chat_pair")
public class ChatPair implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id1")
    private User from;

    @ManyToOne
    @JoinColumn(name = "user_id2")
    private User to;

    public boolean isNew() {
        return this.id == 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User user1) {
        this.from = user1;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User user2) {
        this.to = user2;
    }
}