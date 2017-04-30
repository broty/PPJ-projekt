package cz.tul;

import cz.tul.data.*;
import cz.tul.service.CommentService;
import cz.tul.service.ImageService;
import cz.tul.service.UserService;
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

public class ImageTests {
    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

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
        commentService.deleteComments();
        imageService.deleteImages();
        userService.deleteUsers();

        prepareTestData();
    }

    public void prepareTestData() {
        user1 = new User(new Date(), "Macho1");
        user2 = new User(new Date(), "Macho2");
        user3 = new User(new Date(), "Macho3");
        user4 = new User(new Date(), "Macho4");

        // create users
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        userService.create(user4);

        // prepare images
        image1 = new Image("file:///d:/obrazky/obr1.png", "Obrazek1", new Date(), new Date(), 0, 0, user1);
        image2 = new Image("file:///d:/obrazky/obr2.png", "Obrazek2", new Date(), new Date(), 0, 0, user2);
        image3 = new Image("file:///d:/obrazky/obr3.png", "Obrazek3", new Date(), new Date(), 0, 0, user3);
        image4 = new Image("file:///d:/obrazky/obr4.png", "Obrazek4", new Date(), new Date(), 0, 0, user4);
    }

    @Test
    public void testImages() {
        // create and test 1 image
        imageService.create(image1);

        List<Image> images1 = imageService.getAllImages();

        assertEquals("One image should have been created and retrieved", 1, images1.size());

        assertEquals("Inserted image should match retrieved", image1.getName(), imageService.getImage(image1.getId()).getName());

        // create and test 3 more images
        imageService.create(image2);
        imageService.create(image3);
        imageService.create(image4);

        List<Image> images2 = imageService.getAllImages();
        assertEquals("Should be four retrieved images.", 4, images2.size());

        Image img;
        // test like

        img = imageService.getImage(image1.getId());
        int oldValue = img.getLikes();

        img.setLikes(img.getLikes()+1);
        imageService.update(img);

        assertTrue("Image should have incremented likes.", oldValue < imageService.getImage(image1.getId()).getLikes());

        // test dislike

        img = imageService.getImage(image1.getId());
        oldValue = img.getDislikes();
        img.setDislikes(img.getDislikes()+1);
        imageService.update(img);

        assertTrue("Image should have incremented dislikes.", oldValue < imageService.getImage(image1.getId()).getDislikes());


        // test edit name, edit url
        img = imageService.getImage(image1.getId());
        String oldName = img.getName();
        String oldUrl = img.getUrl();
        Date dateEdit = img.getDateEdit();

        img.setName("Some new name");
        img.setUrl("http://some.test/url");

        imageService.update(img);

        assertTrue("Image name should have been updated.", oldName != imageService.getImage(image1.getId()).getName());
        assertTrue("Image URL should have been updated", oldUrl != imageService.getImage(image1.getId()).getUrl());
        assertNotEquals("Date of edit should have been updated", dateEdit, imageService.getImage(image1.getId()).getDateEdit());
    }
}