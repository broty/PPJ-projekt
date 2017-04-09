package cz.tul;

import cz.tul.data.Komentar;
import cz.tul.data.KomentarsDao;
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

public class KomentarsDaoTests {
    @Autowired
    private KomentarsDao komentarsDao;

    @Test
    public void testKomentars() {

        Komentar komentar = new Komentar("Komentář1", new Date(), new Date(), 0, 0, 1, 1);

        assertTrue("Komentar creation should return true", komentarsDao.create(komentar));

        List<Komentar> komentars = komentarsDao.getAllKomentars();

    }
}
