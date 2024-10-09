package net.therap.ebazar.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hasin.raiyan
 * @since 4/14/21
 */
@Entity
@Table(name = "chat")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "chat_pair_id")
    private ChatPair chatPair;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;

    @NotNull(message = "{chat.text.null.message}")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChatPair getChatPair() {
        return chatPair;
    }

    public void setChatPair(ChatPair chatPair) {
        this.chatPair = chatPair;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}