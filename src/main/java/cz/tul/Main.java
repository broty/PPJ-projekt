package cz.tul;

import cz.tul.data.*;
import cz.tul.provisioning.Provisioner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class Main {

    @Bean
    public OffersDao offersDao() {
        return new OffersDao();
    }

    @Bean
    public UsersDao usersDao() {
        return new UsersDao();
    }

    @Bean
    public AutorsDao autorsDao() {
        return new AutorsDao();
    }

    @Bean
    public KomentarsDao KomentarsDao() {
        return new KomentarsDao();
    }

    @Bean
    public ObrazeksDao obrazeksDao() {
        return new ObrazeksDao();
    }

    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        UsersDao usersDao = ctx.getBean(UsersDao.class);
        AutorsDao autorsDao = ctx.getBean(AutorsDao.class);

        List<User> users = usersDao.getAllUsers();
        System.out.println(users);

    }

}