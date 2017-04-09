package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class AutorsDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Autor autor) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        //params.addValue("idautor", autor.getIdautor());
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("datum_registrace", sdf.format(autor.getDatum_registrace()));
        params.addValue("jmeno", autor.getJmeno());

        return jdbc.update("insert into autor (idautor, datum_registrace, jmeno) values (NULL, :datum_registrace, :jmeno)", params) == 1;
    }

    public boolean exists(String jmeno) {
        return jdbc.queryForObject("select count(*) from autor where jmeno=:jmeno",
                new MapSqlParameterSource("jmeno", jmeno), Integer.class) > 0;
    }

    public List<Autor> getAllAutors() {
        return jdbc.query("select * from autor", BeanPropertyRowMapper.newInstance(Autor.class));
    }

    public void deleteAutors() {
        jdbc.getJdbcOperations().execute("DELETE FROM Autors");
    }
}

