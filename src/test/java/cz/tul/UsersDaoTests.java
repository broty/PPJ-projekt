package cz.tul;

import cz.tul.data.User;
import cz.tul.data.UsersDao;
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

public class UsersDaoTests {
    @Autowired
    private UsersDao usersDao;

    @Test
    public void testUsers() {

        User user = new User(new Date(), "Macho3");

        assertTrue("User creation should return true", usersDao.create(user));

        List<User> users = usersDao.getAllUsers();


        assertTrue("User should exist.", usersDao.exists(user.getName()));


        for(User a : users) {
            System.out.println(a.getName());
        }

    }
}
