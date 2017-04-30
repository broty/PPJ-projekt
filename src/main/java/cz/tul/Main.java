package cz.tul;

import cz.tul.provisioning.Provisioner;
import cz.tul.repositories.ImageRepository;
import cz.tul.service.CommentService;
import cz.tul.service.ImageService;
import cz.tul.service.TagService;
import cz.tul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
public class Main {

    @Bean
    public UserService userService() { return new UserService();}

    @Bean
    public ImageService imageService() { return new ImageService(); }

    @Bean
    public CommentService commentService() { return new CommentService(); }

    @Bean
    public TagService tagService() { return new TagService(); }

    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);
    }

}