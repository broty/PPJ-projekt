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

@Transactional
public class UsersDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public boolean create(User user) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        
        params.addValue("dateCreate", user.getDateCreate());
        params.addValue("name", user.getName());

        return jdbc.update("insert into user (dateCreate, name) values (:dateCreate, :name)", params) == 1;
    }

   public boolean exists(String name) {
        return jdbc.queryForObject("select count(*) from user where name=:name",
                new MapSqlParameterSource("name", name), Integer.class) > 0;
    }

    public List<User> getAllUsers() {
        return jdbc.query("select * from user", BeanPropertyRowMapper.newInstance(User.class));
    }

    public void deleteUsers() {
        jdbc.getJdbcOperations().execute("DELETE FROM user");
    }
}

