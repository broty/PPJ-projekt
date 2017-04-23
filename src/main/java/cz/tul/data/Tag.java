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
    @Column(name = "idtag")
    private int idtag;
    private String value;

    public Tag() {}

    public Tag(String value) {
        this.value = value;
    }

    public Tag(int idtag, String value) {
        this.idtag = idtag;
        this.value = value;
    }

    public int getIdtag() {
        return idtag;
    }

    public void setIdtag(int idtag) {
        this.idtag = idtag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
