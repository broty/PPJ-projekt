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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martin on 09.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TagsDaoTests {
    @Autowired
    private TagsDao tagsDao;

    @Autowired
    private ImagesDao imagesDao;

    @Autowired
    private UsersDao usersDao;

    private User user1;
    private User user2;

    private Image image1;
    private Image image2;

    private Tag tag1 = new Tag("Education");
    private Tag tag2 = new Tag("People");

    @Before
    public void init() {
        imagesDao.deleteImages();
        tagsDao.deleteTags();
        usersDao.deleteUsers();
        prepareTestData();
    }

    public void prepareTestData() {
        // create users
        user1 = new User(new Date(), "Macho1");
        user2 = new User(new Date(), "Macho2");
        usersDao.create(user1);
        usersDao.create(user2);

        // create images
        image1 = new Image("file:///d:/obrazky/obr1.png", "Obrazek1", new Date(), new Date(), 0, 0, user1);
        image2 = new Image("file:///d:/obrazky/obr2.png", "Obrazek2", new Date(), new Date(), 0, 0, user2);
        imagesDao.create(image1);
        imagesDao.create(image2);
    }

    @Test
    public void testTags() {

        tagsDao.create(tag1);

        List<Tag> tags1 = tagsDao.getAllTags();

        assertEquals("One tag should have been created and retrieved", 1, tags1.size());

        assertEquals("Inserted tag should match retrieved", tag1.getValue(), tags1.get(0).getValue());

        tagsDao.create(tag2);

        List<Tag> tags2 = tagsDao.getAllTags();

        assertEquals("Should be two retrieved tags.", 2, tags2.size());

        // test M:N relation of TAG and IMAGE
        // joinTable name = Image_Tag
        Set<Tag> tags = new HashSet<Tag>();
            tags.add(tag1);
            tags.add(tag2);
        image1.setTags(tags);

        Set<Image> images = new HashSet<Image>();
            images.add(image1);
            images.add(image2);
        tag1.setImages(images);

        assertEquals("Expected to retrieve 2 tags.", 2, image1.getTags().size());
        assertEquals("Expected to retrieve 2 images.", 2, tag1.getImages().size());

        assertEquals("Expected to retrieve 0 tags.", 0, image2.getTags().size());
        assertEquals("Expected to retrieve 0 images.", 0, tag2.getImages().size());
    }
}