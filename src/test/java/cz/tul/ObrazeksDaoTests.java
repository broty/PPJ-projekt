package cz.tul;

import cz.tul.data.Obrazek;
import cz.tul.data.ObrazeksDao;
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

public class ObrazeksDaoTests {
    @Autowired
    private ObrazeksDao obrazeksDao;

    @Test
    public void testUsers() {
/*
        Obrazek obrazek = new Obrazek("file:///d:/obrazky/modryportugal.png", "Modrý portugal", new Date(), new Date(), 0, 0, 1);

        assertTrue("Obrazek creation should return true", obrazeksDao.create(obrazek));

        List<Obrazek> obrazeks = obrazeksDao.getAllObrazeks();


        assertTrue("Obrazek should exist.", obrazeksDao.exists(obrazek.getNazev()));


        for(Obrazek o : obrazeks) {
            System.out.println(o.getNazev());
        }

        int lajky1 = obrazeksDao.getLajks(1);
        obrazeksDao.lajk(1);
        int lajky2 = obrazeksDao.getLajks(1);

        assertTrue("Obrazku by mel pribyt lajk", lajky1 < lajky2);

        int dislajky1 = obrazeksDao.getDislajks(1);
        obrazeksDao.dislajk(1);
        int dislajky2 = obrazeksDao.getDislajks(1);

        assertTrue("Obrazku by mel pribyt dislajk", dislajky1 < dislajky2);

        String url1 = obrazeksDao.getUrl(3);
        obrazeksDao.editUrl(3,"file:///d:/obrazek3");
        String url2 = obrazeksDao.getUrl(3);

        assertTrue("Obrazku se nezdařilo změnit URL", url1 != url2);

        String nazev1 = obrazeksDao.getNazev(3);
        obrazeksDao.editNazev(3,"Mokrý pes");
        String nazev2 = obrazeksDao.getNazev(3);

        assertTrue("Obrazku se nezdařilo změnit název", nazev1 != nazev2);
        */

        List<Obrazek> obrazeks = obrazeksDao.getObrazekName("Modrý portugal");

        for(Obrazek o : obrazeks) {
            System.out.println("id: " + o.getIdobrazek() + " nazev: " + o.getNazev());
        }
    }
}
