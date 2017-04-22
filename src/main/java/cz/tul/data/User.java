package cz.tul.data;

import java.util.Date;

/**
 * Created by Martin on 03.04.2017.
 */


public class User {

    private int iduser;

    private Date date_creation;
    private String name;

    public User() {
    }

    public User(Date date_creation, String name) {
        this.date_creation = date_creation;
        this.name = name;
    }

    public User(int iduser, Date date_creation, String name) {
        this.iduser = iduser;
        this.date_creation = date_creation;
        this.name = name;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
