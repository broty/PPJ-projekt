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

public class CommentsDaoTests {
    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ImagesDao imagesDao;

    private User user1;
    private User user2;
    private User user3;
    private User user4;

    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;

    private Comment comment1;
    private Comment comment2;
    private Comment comment3;
    private Comment comment4;


    @Before
    public void init() {
        commentsDao.deleteComments();
        imagesDao.deleteImages();
        usersDao.deleteUsers();

        prepareTestData();
    }

    private void prepareTestData() {
        user1 = new User(new Date(), "Macho11");
        user2 = new User(new Date(), "Macho22");
        user3 = new User(new Date(), "Macho33");
        user4 = new User(new Date(), "Macho44");

        // create users
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        // create images
        image1 = new Image("file:///d:/obrazky/obr1.png", "Obrazek1", new Date(), new Date(), 0, 0, user1);
        image2 = new Image("file:///d:/obrazky/obr2.png", "Obrazek2", new Date(), new Date(), 0, 0, user2);
        image3 = new Image("file:///d:/obrazky/obr3.png", "Obrazek3", new Date(), new Date(), 0, 0, user3);
        image4 = new Image("file:///d:/obrazky/obr4.png", "Obrazek4", new Date(), new Date(), 0, 0, user4);

        imagesDao.create(image1);
        imagesDao.create(image2);
        imagesDao.create(image3);
        imagesDao.create(image4);

        // prepare comments
        comment1 = new Comment("Blabla1", new Date(), new Date(), 0, 0, image1, user4);
        comment2 = new Comment("Blabla2", new Date(), new Date(), 1, 1, image2, user3);
        comment3 = new Comment("Blabla3", new Date(), new Date(), 2, 2, image3, user2);
        comment4 = new Comment("Blabla4", new Date(), new Date(), 3, 3, image4, user1);
    }

    @Test
    public void testComments() {
        // test create comment

        commentsDao.create(comment1);
        int id = comment1.getId();

        List<Comment> comments1 = commentsDao.getAllComments();

        assertEquals("One comment should have been created and retrieved", 1, comments1.size());

        assertEquals("Inserted comment should match retrieved", comment1.getText(), commentsDao.getComment(id).getText());

        commentsDao.create(comment2);
        commentsDao.create(comment3);
        commentsDao.create(comment4);

        List<Comment> comments2 = commentsDao.getAllComments();
        assertEquals("Should be four retrieved comments.", 4, comments2.size());

        // test edit

        Comment comment = commentsDao.getComment(id);

        int likes = comment.getLikes();
        int dislikes = comment.getDislikes();
        String text = comment.getText();
        Date dateEdit = comment.getDateEdit();

        comment.setLikes(likes+1);
        comment.setDislikes(likes+1);
        comment.setText("New text");

        commentsDao.update(comment);
        Comment commentUpdated = commentsDao.getComment(id);
        assertTrue("Likes should have been incremented", likes < commentUpdated.getLikes());
        assertTrue("Dislikes should have been incremented", dislikes < commentUpdated.getDislikes());
        assertTrue("Text of comment should have been changed", text != commentUpdated.getText());
        assertNotEquals("Date of edit should have been updated", dateEdit, commentUpdated.getDateEdit());
    }
}