package cz.tul.data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Martin on 03.04.2017.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @Column(name = "name")
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
