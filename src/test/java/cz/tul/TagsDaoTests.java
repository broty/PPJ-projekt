package cz.tul;

import cz.tul.data.Tag;
import cz.tul.data.TagsDao;
import cz.tul.data.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    private Tag tag1 = new Tag("Education");
    private Tag tag2 = new Tag("People");

    @Before
    public void init() {
        tagsDao.deleteTags();
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
    }
}
