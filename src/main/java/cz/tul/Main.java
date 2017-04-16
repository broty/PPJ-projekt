package cz.tul;

import cz.tul.data.CommentsDao;
import cz.tul.data.ImagesDao;
import cz.tul.data.UsersDao;
import cz.tul.provisioning.Provisioner;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
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

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {return entityManagerFactory.unwrap(SessionFactory.class);}


    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }

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