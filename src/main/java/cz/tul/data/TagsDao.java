package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;



public class TagsDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Tag tag) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("idtag", tag.getIdtag());
        params.addValue("hodnota", tag.getHodnota());

        return jdbc.update("insert into tag (idtag, hodnota) values (:idtag, :hodnota)", params) == 1;
    }
}

// testy: vytvorit uzivatele, vypsat seznam uzivatelu
// pridani obrazku, otestovat ze je pridany
// uzivatel obrazek lajkne, otestovat ze obrazek ma o lajk vic
// test - zmena neceho u obrazku - provedena zmena a aktualizovan datum zmeny
// pridani komentu, overit ze tam je
// vyhledani obrazku podle jmena