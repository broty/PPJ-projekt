package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class TagsDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() { return sessionFactory.getCurrentSession();}

    public void create(Tag tag) {
        session().save(tag);
    }

    public List<Tag> getAllTags() {
        return session().createCriteria(Tag.class).list();
    }

    public void deleteTags() {
        session().createQuery("delete from Tag").executeUpdate();
    }
}