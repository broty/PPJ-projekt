package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
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

    public boolean lajk(int idcomment) {
        Query query = session().createQuery("update Comment set likes = likes + 1");
        return query.executeUpdate() == 1;
    }

    public boolean dislajk(int idcomment) {
        Query query = session().createQuery("update Comment set dislikes = dislikes + 1");
        return query.executeUpdate() == 1;
    }

    public int getLajks(int idcomment) {
        Criteria crit = session().createCriteria(Comment.class);
        crit.add(Restrictions.eq("idcomment", idcomment));
        crit.setProjection(Projections.property("likes"));
        return (int) crit.list().get(0);
    }

    public int getDislajks(int idcomment) {
        Criteria crit = session().createCriteria(Comment.class);
        crit.add(Restrictions.eq("idcomment", idcomment));
        crit.setProjection(Projections.property("dislikes"));
        return (int) crit.list().get(0);
    }


    public boolean editComment(int idcomment, String newText) {
        Query query = session().createQuery("update Comment set text = :newText, date_edit = :datum " +
                "where idcomment = :idcomment");
        query.setParameter("newText", newText);
        query.setParameter("datum", new Date());
        query.setParameter("idcomment", idcomment);

        return query.executeUpdate() > 0;
    }

    public void deleteComments() {
        session().createQuery("delete from Comment").executeUpdate();
    }

    public String getText(int id) {
        Criteria crit = session().createCriteria(Comment.class);
        crit.add(Restrictions.eq("id", id));
        crit.setProjection(Projections.property("text"));
        return crit.list().get(0).toString();
    }

    public String getDateEdit(int id) {
        Criteria crit = session().createCriteria(Comment.class);
        crit.add(Restrictions.eq("id", id));
        crit.setProjection(Projections.property("date_edit"));
        return crit.list().get(0).toString();
    }
}