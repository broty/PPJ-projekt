package cz.tul.data;

import java.util.Date;

/**
 * Created by Martin on 03.04.2017.
 */
public class Obrazek {
    private int idobrazek;
    private String url;
    private String nazev;
    private Date datum_vytvoreni;
    private Date datum_aktualizace;
    private int likes;
    private int dislike;
    private int autor_idautor;

    public Obrazek() {
    }

    public Obrazek(/*int idobrazek, */String url, String nazev, Date datum_vytvoreni,
                   Date datum_aktualizace, int likes, int dislike, int autor_idautor) {
        //this.idobrazek = idobrazek;
        this.url = url;
        this.nazev = nazev;
        this.datum_vytvoreni = datum_vytvoreni;
        this.datum_aktualizace = datum_aktualizace;
        this.likes = likes;
        this.dislike = dislike;
        this.autor_idautor = autor_idautor;
    }

    public int getIdobrazek() {
        return idobrazek;
    }

    public void setIdobrazek(int idobrazek) {
        this.idobrazek = idobrazek;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public Date getDatum_vytvoreni() {
        return datum_vytvoreni;
    }

    public void setDatum_vytvoreni(Date datum_vytvoreni) {
        this.datum_vytvoreni = datum_vytvoreni;
    }

    public Date getDatum_aktualizace() {
        return datum_aktualizace;
    }

    public void setDatum_aktualizace(Date datum_aktualizace) {
        this.datum_aktualizace = datum_aktualizace;
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

    public int getAutor_idautor() {
        return autor_idautor;
    }

    public void setAutor_idautor(int autor_idautor) {
        this.autor_idautor = autor_idautor;
    }
}
