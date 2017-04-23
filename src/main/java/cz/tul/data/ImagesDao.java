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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public class ImagesDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() { return sessionFactory.getCurrentSession();}

    public void create(Image image) {
        session().save(image);
    }

    public List<Image> getAllImages() {
        Criteria crit = session().createCriteria(Image.class);
        return crit.list();
    }

    public List<Image> getImageName(String name) {
        Criteria crit = session().createCriteria(Image.class);
        crit.add(Restrictions.eq("name", name));
        return crit.list();
    }

    public boolean exists(String name) {
        Criteria crit = session().createCriteria(Image.class);
        return crit.setProjection(Projections.rowCount()).uniqueResult().hashCode() > 0;
    }

    public boolean lajk(int idimage) {
        Query query = session().createQuery("update Image set likes = likes + 1");
        return query.executeUpdate() == 1;
    }

    public boolean dislajk(int idimage) {
        Query query = session().createQuery("update Image set dislikes = dislikes + 1");
        return query.executeUpdate() == 1;
    }

    public int getLajks(int idimage) {
        Criteria crit = session().createCriteria(Image.class);
        crit.add(Restrictions.eq("idimage", idimage));
        crit.setProjection(Projections.property("likes"));
        return (int) crit.list().get(0);
    }

    public int getDislajks(int idimage) {
        Criteria crit = session().createCriteria(Image.class);
        crit.add(Restrictions.eq("idimage", idimage));
        crit.setProjection(Projections.property("dislikes"));
        return (int) crit.list().get(0);
    }


    public boolean editName(int idimage, String newName) {
        Query query = session().createQuery("update Image set name = :newName, date_edit = :datum " +
                "where idimage = :idimage");
        query.setParameter("newName", newName);
        query.setParameter("datum", new Date());
        query.setParameter("idimage", idimage);

        return query.executeUpdate() > 0;
    }

    public boolean editUrl(int idimage, String newUrl) {
        Query query = session().createQuery("update Image set url = :newUrl, date_edit = :datum " +
                "where idimage = :idimage");
        query.setParameter("newUrl", newUrl);
        query.setParameter("datum", new Date());
        query.setParameter("idimage", idimage);

        return query.executeUpdate() > 0;
    }

    public String getName(int idimage) {
        Criteria crit = session().createCriteria(Image.class);
        crit.add(Restrictions.eq("idimage", idimage));
        crit.setProjection(Projections.property("name"));
        return (String) crit.list().get(0);
    }

    public String getUrl(int idimage) {
        Criteria crit = session().createCriteria(Image.class);
        crit.add(Restrictions.eq("idimage", idimage));
        crit.setProjection(Projections.property("url"));
        return (String) crit.list().get(0);
    }

    public String getDateEdit(int idimage) {
        Criteria crit = session().createCriteria(Image.class);
        crit.add(Restrictions.eq("idimage", idimage));
        crit.setProjection(Projections.property("date_edit"));
        return crit.list().get(0).toString();
    }

    public void deleteImages() {
        session().createQuery("delete from Image").executeUpdate();
    }
}
