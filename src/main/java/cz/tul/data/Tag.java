package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */
public class Tag {
    private int idtag;
    private String hodnota;

    public Tag() {}

    public Tag(int idtag, String hodnota) {
        this.idtag = idtag;
        this.hodnota = hodnota;
    }

    public int getIdtag() {
        return idtag;
    }

    public void setIdtag(int idtag) {
        this.idtag = idtag;
    }

    public String getHodnota() {
        return hodnota;
    }

    public void setHodnota(String hodnota) {
        this.hodnota = hodnota;
    }
}
