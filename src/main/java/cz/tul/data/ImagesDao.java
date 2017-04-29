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

    public Image getImage(int id) {
        Criteria crit = session().createCriteria(Image.class);
        crit.add(Restrictions.eq("id", id));
        return (Image) crit.uniqueResult();
    }

    public void update(Image image) {
        image.setDateEdit(new Date());
        session().update(image);
    }

    public void deleteImages() {
        session().createQuery("delete from Image").executeUpdate();
    }
}
