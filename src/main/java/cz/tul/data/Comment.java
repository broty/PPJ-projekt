package cz.tul.data;

import java.util.Date;

/**
 * Created by Martin on 03.04.2017.
 */


public class Comment {
    private int id;
    private String text;
    private Date dateCreate;
    private Date dateEdit;
    private int likes;
    private int dislikes;
    private int idImage;
    private int idUser;

    public Comment() {
    }

    public Comment(String text, Date dateCreate, Date dateEdit, int likes,
                   int dislikes, int idImage, int idUser) {
        this.text = text;
        this.dateCreate = dateCreate;
        this.dateEdit = dateEdit;
        this.likes = likes;
        this.dislikes = dislikes;
        this.idImage = idImage;
        this.idUser = idUser;
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

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
