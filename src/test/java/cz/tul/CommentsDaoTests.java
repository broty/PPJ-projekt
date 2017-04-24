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

    private User user1 = new User(new Date(), "Macho1");
    private User user2 = new User(new Date(), "Macho2");
    private User user3 = new User(new Date(), "Macho3");
    private User user4 = new User(new Date(), "Macho4");

    private Image image1 = new Image("file:///d:/obrazky/obr1.png", "Obrazek1", new Date(), new Date(), 0, 0, 1);
    private Image image2 = new Image("file:///d:/obrazky/obr2.png", "Obrazek2", new Date(), new Date(), 0, 0, 2);
    private Image image3 = new Image("file:///d:/obrazky/obr3.png", "Obrazek3", new Date(), new Date(), 0, 0, 3);
    private Image image4 = new Image("file:///d:/obrazky/obr4.png", "Obrazek4", new Date(), new Date(), 0, 0, 4);

    private Comment comment1 = new Comment("Blabla1", new Date(), new Date(), 0, 0, 1, 4);
    private Comment comment2 = new Comment("Blabla2", new Date(), new Date(), 1, 1, 2, 3);
    private Comment comment3 = new Comment("Blabla3", new Date(), new Date(), 2, 2, 3, 2);
    private Comment comment4 = new Comment("Blabla4", new Date(), new Date(), 3, 3, 4, 1);


    @Before
    public void init() {
        usersDao.deleteUsers();
        imagesDao.deleteImages();
        commentsDao.deleteComments();
    }

    @Test
    public void testComments() {
        // create users
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        // create images
        imagesDao.create(image1);
        imagesDao.create(image2);
        imagesDao.create(image3);
        imagesDao.create(image4);

        // test create comment

        commentsDao.create(comment1);

        List<Comment> comments1 = commentsDao.getAllComments();

        assertEquals("One comment should have been created and retrieved", 1, comments1.size());

        assertEquals("Inserted comment should match retrieved", comment1.getText(), comments1.get(0).getText());

        commentsDao.create(comment2);
        commentsDao.create(comment3);
        commentsDao.create(comment4);

        List<Comment> comments2 = commentsDao.getAllComments();
        assertEquals("Should be four retrieved comments.", 4, comments2.size());

        // test like

        int likes1 = comments1.get(0).getLikes();
        commentsDao.lajk(comments1.get(0).getId());

        assertTrue("Comment should have incremented likes", likes1 < commentsDao.getLajks(comments2.get(0).getId()));

        // test dislike

        int dislikes1 = comments1.get(0).getDislikes();
        commentsDao.dislajk(comments1.get(0).getId());

        assertTrue("Comment should have incremented dislikes", dislikes1 < commentsDao.getDislajks(comments2.get(0).getId()));

        // test edit text
        int id = comments2.get(2).getId();
        String text1 = commentsDao.getText(id);
        String editDate1 = commentsDao.getDateEdit(id);

        commentsDao.editComment(id,"New new new new new bla bla bla bla");

        String text2 = commentsDao.getText(id);
        String editDate2 = commentsDao.getDateEdit(id);

        assertTrue("Failed to change image URL.", text1 != text2);
        assertTrue("Edit date should change after URL update", editDate1 != editDate2);

    }
}
