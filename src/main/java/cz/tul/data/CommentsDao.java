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

import java.util.Date;
import java.util.List;

@Transactional
public class CommentsDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() { return sessionFactory.getCurrentSession();}

    public void create(Comment comment) {
        session().save(comment);
    }

    public List<Comment> getAllComments() {
        Criteria crit = session().createCriteria(Comment.class);
        return crit.list();
    }

    public Comment getComment(int id) {
        Criteria crit = session().createCriteria(Comment.class);
        crit.add(Restrictions.eq("id", id));
        return (Comment) crit.uniqueResult();
    }

    public void update(Comment comment) {
        session().update(comment);
    }

    public void update(Comment comment, boolean updateDateEdit) {
        if (updateDateEdit) {
            comment.setDateEdit(new Date());
        }
        update(comment);
    }

    public void deleteComments() {
        session().createQuery("delete from Comment").executeUpdate();
    }
}