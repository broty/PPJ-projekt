package cz.tul;

import cz.tul.data.CommentsDao;
import cz.tul.data.ImagesDao;
import cz.tul.data.TagsDao;
import cz.tul.data.UsersDao;
import cz.tul.provisioning.Provisioner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
public class Main {

    @Bean
    public UsersDao usersDao() {
        return new UsersDao();
    }

    @Bean
    public CommentsDao CommentsDao() {
        return new CommentsDao();
    }

    @Bean
    public ImagesDao imagesDao() {
        return new ImagesDao();
    }

    @Bean
    public TagsDao tagsDao() { return new TagsDao(); }

    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);
/*
        UsersDao usersDao = ctx.getBean(UsersDao.class);
        UsersDao autorsDao = ctx.getBean(UsersDao.class);

        List<User> users = usersDao.getAllUsers();
        System.out.println(users);
*/
    }

}