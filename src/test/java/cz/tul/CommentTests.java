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

public class CommentTests {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

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
        commentService.deleteComments();
        imageService.deleteImages();
        userService.deleteUsers();

        prepareTestData();
    }

    private void prepareTestData() {
        user1 = new User(new Date(), "Macho11");
        user2 = new User(new Date(), "Macho22");
        user3 = new User(new Date(), "Macho33");
        user4 = new User(new Date(), "Macho44");

        // create users
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        userService.create(user4);

        // create images
        image1 = new Image("file:///d:/obrazky/obr1.png", "Obrazek1", new Date(), new Date(), 0, 0, user1);
        image2 = new Image("file:///d:/obrazky/obr2.png", "Obrazek2", new Date(), new Date(), 0, 0, user2);
        image3 = new Image("file:///d:/obrazky/obr3.png", "Obrazek3", new Date(), new Date(), 0, 0, user3);
        image4 = new Image("file:///d:/obrazky/obr4.png", "Obrazek4", new Date(), new Date(), 0, 0, user4);

        imageService.create(image1);
        imageService.create(image2);
        imageService.create(image3);
        imageService.create(image4);

        // prepare comments
        comment1 = new Comment("Blabla1", new Date(), new Date(), 0, 0, image1, user4);
        comment2 = new Comment("Blabla2", new Date(), new Date(), 1, 1, image2, user3);
        comment3 = new Comment("Blabla3", new Date(), new Date(), 2, 2, image3, user2);
        comment4 = new Comment("Blabla4", new Date(), new Date(), 3, 3, image4, user1);
    }

    @Test
    public void testCreateRetrieveComment() {
        commentService.create(comment1);
        int id = comment1.getId();

        List<Comment> comments1 = commentService.getAllComments();

        assertEquals("One comment should have been created and retrieved", 1, comments1.size());

        assertEquals("Inserted comment should match retrieved", comment1.getText(), commentService.getComment(id).getText());

        commentService.create(comment2);
        commentService.create(comment3);
        commentService.create(comment4);

        List<Comment> comments2 = commentService.getAllComments();
        assertEquals("Should be four retrieved comments.", 4, comments2.size());

    }

    @Test
    public void testLikeComment() {
        commentService.create(comment1);
        int id = comment1.getId();

        Comment comment = commentService.getComment(id);
        int likes = comment.getLikes();
        Date dateEdit = comment.getDateEdit();

        comment.setLikes(likes+1);
        commentService.update(comment);

        Comment commentUpdated = commentService.getComment(id);
        assertTrue("Likes should have been incremented", likes < commentUpdated.getLikes());
        assertEquals("Date of edit should have NOT been updated", dateEdit, commentUpdated.getDateEdit());
    }

    @Test
    public void testDislikeComment() {
        commentService.create(comment1);
        int id = comment1.getId();

        Comment comment = commentService.getComment(id);
        int dislikes = comment.getDislikes();
        Date dateEdit = comment.getDateEdit();

        comment.setDislikes(dislikes+1);
        commentService.update(comment);

        Comment commentUpdated = commentService.getComment(id);
        assertTrue("Dislikes should have been incremented", dislikes < commentUpdated.getDislikes());
        assertEquals("Date of edit should have NOT been updated", dateEdit, commentUpdated.getDateEdit());
    }

    @Test
    public void testEditComment() {
        commentService.create(comment1);
        int id = comment1.getId();

        Comment comment = commentService.getComment(id);

        String text = comment.getText();
        Date dateEdit = comment.getDateEdit();
        comment.setText("New text");

        commentService.update(comment, true);
        Comment commentUpdated = commentService.getComment(id);

        assertTrue("Text of comment should have been changed", text != commentUpdated.getText());
        assertNotEquals("Date of edit should have been updated", dateEdit, commentUpdated.getDateEdit());
    }
}