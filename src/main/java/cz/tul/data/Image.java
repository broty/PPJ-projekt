package cz.tul.data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 03.04.2017.
 */

public class Image {
    private int idimage;

    private String url;
    private String name;
    private Date date_creation;
    private Date date_edit;
    private int likes;
    private int dislikes;
    private int user_iduser;

    public Image() {
    }

    public Image(String url, String name, Date date_creation,
                 Date date_edit, int likes, int dislikes, int user_iduser) {
        this.url = url;
        this.name = name;
        this.date_creation = date_creation;
        this.date_edit = date_edit;
        this.likes = likes;
        this.dislikes = dislikes;
        this.user_iduser = user_iduser;
    }

    public int getIdimage() {
        return idimage;
    }

    public void setIdimage(int idimage) {
        this.idimage = idimage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getUser_iduser() {
        return user_iduser;
    }

    public void setUser_iduser(int user_iduser) {
        this.user_iduser = user_iduser;
    }
}
