package cz.tul.data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 03.04.2017.
 */

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idImage")
    private Image image;

    @Column(name = "text")
    private String text;
    private Date dateCreate;
    private Date dateEdit;
    private int likes;
    private int dislikes;

    public Comment() {
    }

    public Comment(String text, Date dateCreate, Date dateEdit, int likes,
                   int dislikes, Image image, User user) {
        this.text = text;
        this.dateCreate = dateCreate;
        this.dateEdit = dateEdit;
        this.likes = likes;
        this.dislikes = dislikes;
        this.image = image;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Date dateEdit) {
        this.dateEdit = dateEdit;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
