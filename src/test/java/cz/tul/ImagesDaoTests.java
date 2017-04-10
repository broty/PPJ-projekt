package cz.tul;

import cz.tul.data.Image;
import cz.tul.data.ImagesDao;
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

public class ImagesDaoTests {
    @Autowired
    private ImagesDao imagesDao;

    @Test
    public void testUsers() {

        Image obrazek = new Image("file:///d:/obrazky/modryportugal.png", "Modrý portugal", new Date(), new Date(), 0, 0, 1);

        assertTrue("Image creation should return true", imagesDao.create(obrazek));

        List<Image> images = imagesDao.getAllImages();


        assertTrue("Image should exist.", imagesDao.exists(obrazek.getName()));


        for(Image o : images) {
            System.out.println(o.getName());
        }

        int lajky1 = imagesDao.getLajks(1);
        imagesDao.lajk(1);
        int lajky2 = imagesDao.getLajks(1);

        assertTrue("Obrazku by mel pribyt lajk", lajky1 < lajky2);

        int dislajky1 = imagesDao.getDislajks(1);
        imagesDao.dislajk(1);
        int dislajky2 = imagesDao.getDislajks(1);

        assertTrue("Obrazku by mel pribyt dislajk", dislajky1 < dislajky2);

        String url1 = imagesDao.getUrl(3);
        imagesDao.editUrl(3,"file:///d:/obrazek3");
        String url2 = imagesDao.getUrl(3);

        assertTrue("Obrazku se nezdařilo změnit URL", url1 != url2);

        String nazev1 = imagesDao.getName(3);
        imagesDao.editName(3,"Mokrý pes");
        String nazev2 = imagesDao.getName(3);

        assertTrue("Obrazku se nezdařilo změnit název", nazev1 != nazev2);


        images = imagesDao.getImageName("Modrý portugal");

        for(Image o : images) {
            System.out.println("id: " + o.getIdimage() + " nazev: " + o.getName());
        }
    }
}
