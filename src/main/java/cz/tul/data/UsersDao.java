package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() { return sessionFactory.getCurrentSession();}

    public boolean create(User user) {
/*
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("date_creation", sdf.format(user.getDate_creation()));
        params.addValue("name", user.getName());

        return jdbc.update("insert into user (iduser, date_creation, name) values (NULL, :date_creation, :name)", params) == 1;*/

        return (boolean) session().save(user);
    }

   public boolean exists(String name) {
        /*return jdbc.queryForObject("select count(*) from user where name=:name",
                new MapSqlParameterSource("name", name), Integer.class) > 0;*/
       Criteria crit = session().createCriteria(User.class);
       crit.add(Restrictions.idEq(name));
       User user = (User) crit.uniqueResult();
       return user != null;
    }


    public List<User> getAllUsers() {
        //return jdbc.query("select * from user", BeanPropertyRowMapper.newInstance(User.class));
        Criteria crit = session().createCriteria(User.class);
        return crit.list();
    }

    public void deleteUsers() {
        //jdbc.getJdbcOperations().execute("DELETE FROM users");
        session().createQuery("delete from User").executeUpdate();
    }
}

