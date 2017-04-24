package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */
public class Tag {
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
