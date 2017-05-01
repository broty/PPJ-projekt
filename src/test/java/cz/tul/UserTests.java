package cz.tul;

import cz.tul.data.User;
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

/**
 * Created by Martin on 09.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UserTests {
    @Autowired
    private UserService userService;

    private User user1 = new User(new Date(), "Macho1");
    private User user2 = new User(new Date(), "Macho2");
    private User user3 = new User(new Date(), "Macho3");
    private User user4 = new User(new Date(), "Macho4");


    @Before
    public void init() {
        userService.deleteUsers();
    }

    @Test
    public void testCreateRetrieveUsers() {

        userService.create(user1);
        int id = user1.getId();

        List<User> users1 = userService.getAllUsers();

        assertEquals("One user should have been created and retrieved", 1, users1.size());

        assertEquals("Inserted user should match retrieved", user1.getName(), userService.getUser(id).getName());

        userService.create(user2);
        userService.create(user3);
        userService.create(user4);

        List<User> users2 = userService.getAllUsers();

        assertEquals("Should be four retrieved users.", 4, users2.size());
    }
}