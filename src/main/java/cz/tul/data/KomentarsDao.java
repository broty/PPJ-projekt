package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public class KomentarsDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Komentar komentar) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idkomentar", komentar.getIdkomentar());
        params.addValue("text", komentar.getText());
        params.addValue("datum_vytvoreni", sdf.format(komentar.getDatum_vytvoreni()));
        params.addValue("datum_edit", sdf.format(komentar.getDatum_edit()));
        params.addValue("likes", komentar.getLikes());
        params.addValue("dislike", komentar.getDislike());
        params.addValue("obrazek_idobrazek", komentar.getObrazek_idobrazek());
        params.addValue("autor_idautor", komentar.getAutor_idautor());

        return jdbc.update("insert into komentar (idkomentar, text, datum_vytvoreni, datum_edit, likes, dislike, obrazek_idobrazek, autor_idautor) values (NULL, :text, :datum_vytvoreni, :datum_edit, :likes, :dislike, :obrazek_idobrazek, :autor_idautor)", params) == 1;
    }

    public List<Komentar> getAllKomentars() {
        return jdbc.query("select * from komentar", BeanPropertyRowMapper.newInstance(Komentar.class));
    }

    public boolean lajk(int idkomentar) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        return jdbc.update("UPDATE `komentar` SET likes = likes + 1 WHERE `idkomentar` = " + idkomentar, param) == 1;
    }

    public boolean dislajk(int idkomentar) {
        MapSqlParameterSource par = new MapSqlParameterSource();
        return jdbc.update("UPDATE `komentar` SET dislike = dislike + 1 WHERE `idkomentar` = " + idkomentar, par) == 1;
    }

    public int getLajks(int idkomentar) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idkomentar", idkomentar);

        return jdbc.queryForObject("select likes from komentar where idkomentar = :idkomentar", params, Integer.class);
    }

    public int getDislajks(int idkomentar) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idkomentar", idkomentar);

        return jdbc.queryForObject("select dislike from komentar where idkomentar = :idkomentar", params, Integer.class);
    }


    public boolean editKomentar(int idkomentar, String newKomentar) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idkomentar", idkomentar);
        params.addValue("newKomentar", newKomentar);
        params.addValue("datum", sdf.format(new Date()));
        return jdbc.update("UPDATE `komentar` SET text = :newKomentar, datum_aktualizace = :datum " +
                "WHERE `idkomentar` = :idkomentar", params) == 1;
    }
}