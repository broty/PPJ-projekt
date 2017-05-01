package cz.tul;

import cz.tul.data.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Martin on 09.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ImagesDaoTests {
    @Autowired
    private ImagesDao imagesDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private CommentsDao commentsDao;

    private User user1;
    private User user2;
    private User user3;
    private User user4;

    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;


    @Before
    public void init() {
        commentsDao.deleteComments();
        imagesDao.deleteImages();
        usersDao.deleteUsers();

        prepareTestData();
    }

    public void prepareTestData() {
        user1 = new User(new Date(), "Macho1");
        user2 = new User(new Date(), "Macho2");
        user3 = new User(new Date(), "Macho3");
        user4 = new User(new Date(), "Macho4");

        // create users
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        // prepare images
        image1 = new Image("file:///d:/obrazky/obr1.png", "Obrazek1", new Date(), new Date(), 0, 0, user1);
        image2 = new Image("file:///d:/obrazky/obr2.png", "Obrazek2", new Date(), new Date(), 0, 0, user2);
        image3 = new Image("file:///d:/obrazky/obr3.png", "Obrazek3", new Date(), new Date(), 0, 0, user3);
        image4 = new Image("file:///d:/obrazky/obr4.png", "Obrazek4", new Date(), new Date(), 0, 0, user4);
    }

    @Test
    public void testCreateRetrieveImages() {
        imagesDao.create(image1);

        List<Image> images1 = imagesDao.getAllImages();

        assertEquals("One image should have been created and retrieved", 1, images1.size());

        assertEquals("Inserted image should match retrieved", image1.getName(), imagesDao.getImage(image1.getId()).getName());

        // create and test 3 more images
        imagesDao.create(image2);
        imagesDao.create(image3);
        imagesDao.create(image4);

        List<Image> images2 = imagesDao.getAllImages();
        assertEquals("Should be four retrieved images.", 4, images2.size());
    }

    @Test
    public void testLikeImage() {
        imagesDao.create(image1);
        Image img = imagesDao.getImage(image1.getId());
        int oldValue = img.getLikes();
        Date dateEdit = img.getDateEdit();

        img.setLikes(img.getLikes()+1);
        imagesDao.update(img);

        assertTrue("Image should have incremented likes.", oldValue < imagesDao.getImage(image1.getId()).getLikes());
        assertEquals("Date of edit should have NOT been updated", dateEdit, imagesDao.getImage(image1.getId()).getDateEdit());
    }

    @Test
    public void testDislikeImage() {
        imagesDao.create(image1);
        Image img = imagesDao.getImage(image1.getId());
        int oldValue = img.getDislikes();
        Date dateEdit = img.getDateEdit();

        img.setDislikes(img.getDislikes() + 1);
        imagesDao.update(img);

        assertTrue("Image should have incremented dislikes.", oldValue < imagesDao.getImage(image1.getId()).getDislikes());
        assertEquals("Date of edit should have NOT been updated", dateEdit, imagesDao.getImage(image1.getId()).getDateEdit());
    }
    @Test
    public void testEditName() {
        imagesDao.create(image1);
        Image img;
        img = imagesDao.getImage(image1.getId());
        String oldName = img.getName();
        Date dateEdit = img.getDateEdit();

        img.setName("Some new name");

        imagesDao.update(img, true);

        assertTrue("Image name should have been updated.", oldName != imagesDao.getImage(image1.getId()).getName());
        assertNotEquals("Date of edit should have been updated", dateEdit, imagesDao.getImage(image1.getId()).getDateEdit());
    }

    @Test
    public void testEditUrl() {
        imagesDao.create(image1);
        Image img;
        img = imagesDao.getImage(image1.getId());
        String oldUrl = img.getUrl();
        Date dateEdit = img.getDateEdit();

        img.setUrl("http://some.test/url");

        imagesDao.update(img, true);

        assertTrue("Image URL should have been updated", oldUrl != imagesDao.getImage(image1.getId()).getUrl());
        assertNotEquals("Date of edit should have been updated", dateEdit, imagesDao.getImage(image1.getId()).getDateEdit());
    }
}