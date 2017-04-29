package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UsersDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    private KeyHolder keyHolder = new GeneratedKeyHolder();

    public int getLastKey() {
        return (int) keyHolder.getKey();
    }

    public void create(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        
        params.addValue("dateCreate", user.getDateCreate());
        params.addValue("name", user.getName());

        jdbc.update("insert into user (dateCreate, name) values (:dateCreate, :name)", params, this.keyHolder);
    }

    public User getUser(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbc.queryForObject("select * from user where id=:id", params, BeanPropertyRowMapper.newInstance(User.class));
    }

    public List<User> getAllUsers() {
        return jdbc.query("select * from user", BeanPropertyRowMapper.newInstance(User.class));
    }

    public void deleteUsers() {
        jdbc.getJdbcOperations().execute("DELETE FROM user");
    }
}

