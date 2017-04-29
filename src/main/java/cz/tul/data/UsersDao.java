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

    public User getUser(int id) {
        Criteria crit = session().createCriteria(User.class);
        crit.add(Restrictions.eq("id", id));
        return (User) crit.uniqueResult();
    }

    public List<User> getAllUsers() {
        Criteria crit = session().createCriteria(User.class);
        return crit.list();
    }

    public void deleteUsers() {
        session().createQuery("delete from User").executeUpdate();
    }
}

