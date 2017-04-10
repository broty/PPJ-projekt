package cz.tul;

import cz.tul.data.Comment;
import cz.tul.data.CommentsDao;
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

import static org.junit.Assert.assertTrue;

/**
 * Created by Martin on 09.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"default"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CommentsDaoTests {
    @Autowired
    private CommentsDao commentsDao;

    @Test
    public void testKomentars() {

        Comment comment = new Comment("Komentář1", new Date(), new Date(), 0, 0, 1, 1);

        assertTrue("Comment creation should return true", commentsDao.create(comment));

        List<Comment> comments = commentsDao.getAllComments();

    }
}
