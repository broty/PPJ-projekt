package cz.tul.data;

import java.util.Date;

/**
 * Created by Martin on 03.04.2017.
 */
public class Komentar {
    private int idkomentar;
    private String text;
    private Date datum_vytvoreni;
    private Date datum_edit;
    private int likes;
    private int dislike;
    private int obrazek_idobrazek;
    private int autor_idautor;

    public Komentar() {
    }

    public Komentar(/*int idkomentar, */String text, Date datum_vytvoreni, Date datum_edit, int likes,
                    int dislike, int obrazek_idobrazek, int autor_idautor) {
        //this.idkomentar = idkomentar;
        this.text = text;
        this.datum_vytvoreni = datum_vytvoreni;
        this.datum_edit = datum_edit;
        this.likes = likes;
        this.dislike = dislike;
        this.obrazek_idobrazek = obrazek_idobrazek;
        this.autor_idautor = autor_idautor;
    }

    public int getIdkomentar() {
        return idkomentar;
    }

    public void setIdkomentar(int idkomentar) {
        this.idkomentar = idkomentar;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDatum_vytvoreni() {
        return datum_vytvoreni;
    }

    public void setDatum_vytvoreni(Date datum_vytvoreni) {
        this.datum_vytvoreni = datum_vytvoreni;
    }

    public Date getDatum_edit() {
        return datum_edit;
    }

    public void setDatum_edit(Date datum_edit) {
        this.datum_edit = datum_edit;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getObrazek_idobrazek() {
        return obrazek_idobrazek;
    }

    public void setObrazek_idobrazek(int obrazek_idobrazek) {
        this.obrazek_idobrazek = obrazek_idobrazek;
    }

    public int getAutor_idautor() {
        return autor_idautor;
    }

    public void setAutor_idautor(int autor_idautor) {
        this.autor_idautor = autor_idautor;
    }
}
