package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Image;
import cz.tul.service.ImageService;
import cz.tul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Martin on 25.05.2017.
 */
@RestController
public class UserController {
    private UserService usersService;
    private ImageService imagesService;

    @Autowired
    public void setImagesService(ImageService imagesService) {
        this.imagesService = imagesService;
    }

    @Autowired
    public void setUsersService(UserService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = ServerApi.USER_PATH + "/images", method = RequestMethod.GET)
    public ResponseEntity<List<Image>> getUserImages(@PathVariable int id) {
        if (usersService.getUser(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Image> images = imagesService.getUserImages(id);
        if (images.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(images, HttpStatus.OK);
    }


}
