package cz.tul;

import cz.tul.data.Autor;
import cz.tul.data.AutorsDao;
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

public class AutorsDaoTests {
    @Autowired
    private AutorsDao autorsDao;

    @Test
    public void testUsers() {

        Autor autor = new Autor(new Date(), "Macho3");

        assertTrue("User creation should return true", autorsDao.create(autor));

        List<Autor> autors = autorsDao.getAllAutors();


        assertTrue("User should exist.", autorsDao.exists(autor.getJmeno()));


        for(Autor a : autors) {
            System.out.println(a.getJmeno());
        }

    }
}
