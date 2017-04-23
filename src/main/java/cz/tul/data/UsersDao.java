package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UsersDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() { return sessionFactory.getCurrentSession();}

    public void create(User user) {
        session().save(user);
    }

   public boolean exists(String name) {
       Criteria crit = session().createCriteria(User.class);
       crit.add(Restrictions.like("name", name));
       User user = (User) crit.uniqueResult();
       return user != null;
    }


    public List<User> getAllUsers() {
        Criteria crit = session().createCriteria(User.class);
        return crit.list();
    }

    public void deleteUsers() {
        //jdbc.getJdbcOperations().execute("DELETE FROM users");
        session().createQuery("delete from User").executeUpdate();
    }
}

