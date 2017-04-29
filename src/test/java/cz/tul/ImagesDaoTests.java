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
        int id1 = (int)usersDao.getLastKey();
        usersDao.create(user2);
        int id2 =(int)usersDao.getLastKey();
        usersDao.create(user3);
        int id3 = (int)usersDao.getLastKey();
        usersDao.create(user4);
        int id4 = (int)usersDao.getLastKey();

        // prepare images
        image1 = new Image("file:///d:/obrazky/obr1.png", "Obrazek1", new Date(), new Date(), 0, 0, id1);
        image2 = new Image("file:///d:/obrazky/obr2.png", "Obrazek2", new Date(), new Date(), 0, 0, id2);
        image3 = new Image("file:///d:/obrazky/obr3.png", "Obrazek3", new Date(), new Date(), 0, 0, id3);
        image4 = new Image("file:///d:/obrazky/obr4.png", "Obrazek4", new Date(), new Date(), 0, 0, id4);
    }

    @Test
    public void testImages() {
        // create and test 1 image
        imagesDao.create(image1);
        int id = (int)imagesDao.getLastKey();

        List<Image> images1 = imagesDao.getAllImages();

        assertEquals("One image should have been created and retrieved", 1, images1.size());

        assertEquals("Inserted image should match retrieved", image1.getName(), imagesDao.getImage(id).getName());

        // create and test 3 more images
        imagesDao.create(image2);
        imagesDao.create(image3);
        imagesDao.create(image4);

        List<Image> images2 = imagesDao.getAllImages();
        assertEquals("Should be four retrieved images.", 4, images2.size());

        Image img;
        // test like

        img = imagesDao.getImage(id);
        int oldValue = img.getLikes();

        img.setLikes(img.getLikes()+1);
        imagesDao.update(img);

        assertTrue("Image should have incremented likes.", oldValue < imagesDao.getImage(id).getLikes());

        // test dislike

        img = imagesDao.getImage(id);
        oldValue = img.getDislikes();
        img.setDislikes(img.getDislikes()+1);
        imagesDao.update(img);

        assertTrue("Image should have incremented dislikes.", oldValue < imagesDao.getImage(id).getDislikes());


        // test edit name, edit url
        img = imagesDao.getImage(id);
        String oldName = img.getName();
        String oldUrl = img.getUrl();
        Date dateEdit = img.getDateEdit();

        img.setName("Some new name");
        img.setUrl("http://some.test/url");

        imagesDao.update(img);

        assertTrue("Image name should have been updated.", oldName != imagesDao.getImage(id).getName());
        assertTrue("Image URL should have been updated", oldUrl != imagesDao.getImage(id).getUrl());
        assertNotEquals("Date of edit should have been updated", dateEdit, imagesDao.getImage(id).getDateEdit());
    }
}
