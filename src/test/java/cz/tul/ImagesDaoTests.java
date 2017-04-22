package cz.tul;

import cz.tul.data.Image;
import cz.tul.data.ImagesDao;
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
import static org.junit.Assert.assertTrue;

/**
 * Created by Martin on 09.04.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ImagesDaoTests {
    @Autowired
    private ImagesDao imagesDao;

    @Autowired
    private UsersDao usersDao;

    private User user1 = new User(new Date(), "Macho1");
    private User user2 = new User(new Date(), "Macho2");
    private User user3 = new User(new Date(), "Macho3");
    private User user4 = new User(new Date(), "Macho4");

    private Image image1 = new Image("file:///d:/obrazky/obr1.png", "Obrazek1", new Date(), new Date(), 0, 0, 1);
    private Image image2 = new Image("file:///d:/obrazky/obr2.png", "Obrazek2", new Date(), new Date(), 0, 0, 2);
    private Image image3 = new Image("file:///d:/obrazky/obr3.png", "Obrazek3", new Date(), new Date(), 0, 0, 3);
    private Image image4 = new Image("file:///d:/obrazky/obr4.png", "Obrazek4", new Date(), new Date(), 0, 0, 4);


    @Before
    public void init() {
        usersDao.deleteUsers();
        imagesDao.deleteImages();
    }

    @Test
    public void testImages() {

        // create users
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        // create and test 1 image
        imagesDao.create(image1);

        List<Image> images1 = imagesDao.getAllImages();

        assertEquals("One image should have been created and retrieved", 1, images1.size());

        assertEquals("Inserted image should match retrieved", image1.getName(), images1.get(0).getName());

        // create and test 3 more images
        imagesDao.create(image2);
        imagesDao.create(image3);
        imagesDao.create(image4);

        List<Image> images2 = imagesDao.getAllImages();
        assertEquals("Should be four retrieved images.", 4, images2.size());

        assertTrue("Image should exist.", imagesDao.exists(image3.getName()));

        // test like

        int likes1 = images2.get(0).getLikes();
        imagesDao.lajk(images2.get(0).getIdimage());

        assertTrue("Image should have incremented likes", likes1 < imagesDao.getLajks(images2.get(0).getIdimage()));

        // test dislike

        int dislikes1 = images2.get(0).getDislikes();
        imagesDao.dislajk(images2.get(0).getIdimage());

        assertTrue("Image should have incremented dislikes", dislikes1 < imagesDao.getDislajks(images2.get(0).getIdimage()));

        // test change url
        int idimg = images2.get(2).getIdimage();
        String url1 = imagesDao.getUrl(idimg);
        String editDate1 = imagesDao.getDateEdit(idimg);

        imagesDao.editUrl(idimg,"file:///d:/obrazek3");

        String url2 = imagesDao.getUrl(idimg);
        String editDate2 = imagesDao.getDateEdit(idimg);

        assertTrue("Failed to change image URL.", url1 != url2);
        assertTrue("Edit date should change after URL update", editDate1 != editDate2);

        // test change name
        idimg = images2.get(1).getIdimage();
        String name1 = imagesDao.getName(idimg);
        editDate1 = imagesDao.getDateEdit(idimg);

        imagesDao.editName(idimg,"Mokrý pes");

        String name2 = imagesDao.getName(idimg);
        editDate2 = imagesDao.getDateEdit(idimg);

        assertTrue("Failed to change image NAME", name1 != name2);
        assertTrue("Edit date should change after NAME update", editDate1 != editDate2);
    }
}
