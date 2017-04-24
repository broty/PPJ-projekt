package cz.tul.data;

import java.util.Date;

/**
 * Created by Martin on 03.04.2017.
 */


public class User {

    private int id;

    private Date dateCreate;
    private String name;

    public User() {
    }

    public User(Date dateCreate, String name) {
        this.dateCreate = dateCreate;
        this.name = name;
    }

    public User(int id, Date dateCreate, String name) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
