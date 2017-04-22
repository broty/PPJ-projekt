package cz.tul;

import cz.tul.data.User;
import cz.tul.data.UsersDao;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Martin on 09.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UsersDaoTests {
    @Autowired
    private UsersDao usersDao;

    private User user1 = new User(new Date(), "Macho1");
    private User user2 = new User(new Date(), "Macho2");
    private User user3 = new User(new Date(), "Macho3");
    private User user4 = new User(new Date(), "Macho4");


    @Before
    public void init() {
        usersDao.deleteUsers();
    }

    @Test
    public void testUsers() {

        usersDao.create(user1);

        List<User> users1 = usersDao.getAllUsers();

        assertEquals("One user should have been created and retrieved", 1, users1.size());

        assertEquals("Inserted user should match retrieved", user1.getName(), users1.get(0).getName());

        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        List<User> users2 = usersDao.getAllUsers();

        assertEquals("Should be four retrieved users.", 4, users2.size());


        assertTrue("User should exist.", usersDao.exists(user2.getName()));
        assertFalse("User should not exist.", usersDao.exists("xkjhsfjlsjf"));
    }
}