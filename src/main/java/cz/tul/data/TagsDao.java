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


public class TagsDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Tag tag) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("idtag", tag.getId());
        params.addValue("value", tag.getValue());

        return jdbc.update("insert into tag (value) values (:value)", params) == 1;
    }

    public List<Tag> getAllTags() {
        return jdbc.query("select * from tag", BeanPropertyRowMapper.newInstance(Tag.class));
    }

    public void deleteTags() {
        jdbc.getJdbcOperations().execute("DELETE FROM tag");
    }
}
