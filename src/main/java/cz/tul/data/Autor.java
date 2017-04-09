package cz.tul.data;

import java.util.Date;

/**
 * Created by Martin on 03.04.2017.
 */
public class Autor {
    private int idautor;
    private Date datum_registrace;
    private String jmeno;

    public Autor() {
    }

    public Autor(/*int idautor, */Date datum_registrace, String jmeno) {
        //this.idautor = idautor;
        this.datum_registrace = datum_registrace;
        this.jmeno = jmeno;
    }

    public int getIdautor() {
        return idautor;
    }

    public void setIdautor(int idautor) {
        this.idautor = idautor;
    }

    public Date getDatum_registrace() {
        return datum_registrace;
    }

    public void setDatum_registrace(Date datum_registrace) {
        this.datum_registrace = datum_registrace;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }
}
