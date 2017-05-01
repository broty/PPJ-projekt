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
        int idUser1 = (int)usersDao.getLastKey();
        usersDao.create(user2);
        int idUser2 =(int)usersDao.getLastKey();
        usersDao.create(user3);
        int idUser3 = (int)usersDao.getLastKey();
        usersDao.create(user4);
        int idUser4 = (int)usersDao.getLastKey();

        // create images
        image1 = new Image("file:///d:/obrazky/obr1.png", "Obrazek1", new Date(), new Date(), 0, 0, idUser1);
        image2 = new Image("file:///d:/obrazky/obr2.png", "Obrazek2", new Date(), new Date(), 0, 0, idUser2);
        image3 = new Image("file:///d:/obrazky/obr3.png", "Obrazek3", new Date(), new Date(), 0, 0, idUser3);
        image4 = new Image("file:///d:/obrazky/obr4.png", "Obrazek4", new Date(), new Date(), 0, 0, idUser4);

        imagesDao.create(image1);
        int idImage1 = (int) imagesDao.getLastKey();
        imagesDao.create(image2);
        int idImage2 = (int) imagesDao.getLastKey();
        imagesDao.create(image3);
        int idImage3 = (int) imagesDao.getLastKey();
        imagesDao.create(image4);
        int idImage4 = (int) imagesDao.getLastKey();

        // prepare comments
        comment1 = new Comment("Blabla1", new Date(), new Date(), 0, 0, idImage1, idUser4);
        comment2 = new Comment("Blabla2", new Date(), new Date(), 1, 1, idImage2, idUser3);
        comment3 = new Comment("Blabla3", new Date(), new Date(), 2, 2, idImage3, idUser2);
        comment4 = new Comment("Blabla4", new Date(), new Date(), 3, 3, idImage4, idUser1);
    }

    @Test
    public void testCreateRetrieveComments() {
        commentsDao.create(comment1);
        int id = commentsDao.getLastKey();

        List<Comment> comments1 = commentsDao.getAllComments();

        assertEquals("One comment should have been created and retrieved", 1, comments1.size());

        assertEquals("Inserted comment should match retrieved", comment1.getText(), commentsDao.getComment(id).getText());

        commentsDao.create(comment2);
        commentsDao.create(comment3);
        commentsDao.create(comment4);

        List<Comment> comments2 = commentsDao.getAllComments();
        assertEquals("Should be four retrieved comments.", 4, comments2.size());
    }

    @Test
    public void testLikeComment() {
        commentsDao.create(comment1);
        int id = commentsDao.getLastKey();
        Comment comment = commentsDao.getComment(id);

        int likes = comment.getLikes();
        Date dateEdit = comment.getDateEdit();

        comment.setLikes(likes+1);

        commentsDao.update(comment);
        Comment commentUpdated = commentsDao.getComment(id);
        assertTrue("Likes should have been incremented", likes < commentUpdated.getLikes());
        assertEquals("Date of edit should have NOT been updated", dateEdit, commentUpdated.getDateEdit());
    }

    @Test
    public void testDislikeComment() {
        commentsDao.create(comment1);
        int id = commentsDao.getLastKey();
        Comment comment = commentsDao.getComment(id);

        int dislikes = comment.getDislikes();
        Date dateEdit = comment.getDateEdit();

        comment.setDislikes(dislikes+1);
        commentsDao.update(comment);

        Comment commentUpdated = commentsDao.getComment(id);
        assertTrue("Dislikes should have been incremented", dislikes < commentUpdated.getDislikes());
        assertEquals("Date of edit should have NOT been updated", dateEdit, commentUpdated.getDateEdit());
    }

    @Test
    public void testEditComment() {
        commentsDao.create(comment1);
        int id = commentsDao.getLastKey();
        Comment comment = commentsDao.getComment(id);

        String text = comment.getText();
        Date dateEdit = comment.getDateEdit();

        comment.setText("New text");

        commentsDao.update(comment, true);
        Comment commentUpdated = commentsDao.getComment(id);
        assertTrue("Text of comment should have been changed", text != commentUpdated.getText());
        assertNotEquals("Date of edit should have been updated", dateEdit, commentUpdated.getDateEdit());
    }
}
