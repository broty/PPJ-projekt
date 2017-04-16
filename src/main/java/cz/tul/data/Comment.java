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
    @Column(name = "idcomment")
    private int idcomment;

    @Column(name = "text")
    private String text;
    private Date date_creation;
    private Date date_edit;
    private int likes;
    private int dislikes;
    private int image_idimage;
    private int user_iduser;

    public Comment() {
    }

    public Comment(String text, Date date_creation, Date date_edit, int likes,
                   int dislikes, int image_idimage, int user_iduser) {
        this.text = text;
        this.date_creation = date_creation;
        this.date_edit = date_edit;
        this.likes = likes;
        this.dislikes = dislikes;
        this.image_idimage = image_idimage;
        this.user_iduser = user_iduser;
    }

    public int getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(int idcomment) {
        this.idcomment = idcomment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_edit() {
        return date_edit;
    }

    public void setDate_edit(Date date_edit) {
        this.date_edit = date_edit;
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

    public int getImage_idimage() {
        return image_idimage;
    }

    public void setImage_idimage(int image_idimage) {
        this.image_idimage = image_idimage;
    }

    public int getUser_iduser() {
        return user_iduser;
    }

    public void setUser_iduser(int user_iduser) {
        this.user_iduser = user_iduser;
    }
}
