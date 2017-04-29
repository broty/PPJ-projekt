package cz.tul.data;

import javax.persistence.*;

/**
 * Created by Martin on 03.04.2017.
 */

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String value;

    public Tag() {}

    public Tag(String value) {
        this.value = value;
    }

    public Tag(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
