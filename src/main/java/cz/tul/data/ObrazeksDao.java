package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;


public class ObrazeksDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Obrazek obrazek) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idobrazek", obrazek.getIdobrazek());
        params.addValue("url", obrazek.getUrl());
        params.addValue("nazev", obrazek.getNazev());
        params.addValue("datum_vytvoreni", sdf.format(obrazek.getDatum_vytvoreni()));
        params.addValue("datum_aktualizace", sdf.format(obrazek.getDatum_aktualizace()));
        params.addValue("likes", obrazek.getLikes());
        params.addValue("dislike", obrazek.getDislike());
        params.addValue("autor_idautor", obrazek.getAutor_idautor());

        return jdbc.update("insert into obrazek (idobrazek, url, nazev, datum_vytvoreni, datum_aktualizace, likes, dislike, autor_idautor) values (NULL, :url, :nazev, :datum_vytvoreni, :datum_aktualizace, :likes, :dislike, :autor_idautor)", params) == 1;
    }

    public List<Obrazek> getAllObrazeks() {
        return jdbc.query("select * from obrazek", BeanPropertyRowMapper.newInstance(Obrazek.class));
    }

    public List<Obrazek> getObrazekName(String nazev) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nazev", nazev);
        return jdbc.query("select * from obrazek WHERE nazev = :nazev", params, BeanPropertyRowMapper.newInstance(Obrazek.class));
    }

    public boolean exists(String nazev) {
        return jdbc.queryForObject("select count(*) from obrazek where nazev=:nazev",
                new MapSqlParameterSource("nazev", nazev), Integer.class) > 0;
    }

    public boolean lajk(int idobrazek) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        return jdbc.update("UPDATE `obrazek` SET likes = likes + 1 WHERE `idobrazek` = " + idobrazek, param) == 1;
    }

    public boolean dislajk(int idobrazek) {
        MapSqlParameterSource par = new MapSqlParameterSource();
        return jdbc.update("UPDATE `obrazek` SET dislike = dislike + 1 WHERE `idobrazek` = " + idobrazek, par) == 1;
    }

    public int getLajks(int idobrazek) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idobrazek", idobrazek);

         return jdbc.queryForObject("select likes from obrazek where idobrazek = :idobrazek", params, Integer.class);
    }

    public int getDislajks(int idobrazek) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idobrazek", idobrazek);

        return jdbc.queryForObject("select dislike from obrazek where idobrazek = :idobrazek", params, Integer.class);
    }


    public boolean editNazev(int idobrazek, String newNazev) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idobrazek", idobrazek);
        params.addValue("newNazev", newNazev);
        params.addValue("datum", sdf.format(new Date()));
        return jdbc.update("UPDATE `obrazek` SET nazev = :newNazev, datum_aktualizace = :datum " +
                "WHERE `idobrazek` = :idobrazek", params) == 1;
    }

    public boolean editUrl(int idobrazek, String newUrl) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idobrazek", idobrazek);
        params.addValue("newUrl", newUrl);
        params.addValue("datum", sdf.format(new Date()));
        return jdbc.update("UPDATE `obrazek` SET url = :newUrl, datum_aktualizace = :datum " +
                "WHERE `idobrazek` = :idobrazek", params) == 1;
    }

    public String getNazev(int idobrazek) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idobrazek", idobrazek);

        return jdbc.queryForObject("select nazev from obrazek where idobrazek = :idobrazek", params, String.class);
    }

    public String getUrl(int idobrazek) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idobrazek", idobrazek);

        return jdbc.queryForObject("select url from obrazek where idobrazek = :idobrazek", params, String.class);
    }

    public String getDatumAktualizace(int idobrazek) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idobrazek", idobrazek);

        return jdbc.queryForObject("select datum_aktualizace from obrazek where idobrazek = :idobrazek", params, String.class);
    }
}
