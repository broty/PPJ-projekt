package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Image;
import cz.tul.service.ImageService;
import cz.tul.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Martin on 26.05.2017.
 */

@RestController
public class TagController {
    private TagService tagsService;
    private ImageService imagesService;

    @Autowired
    public void setImagesService(TagService tagsService) {
        this.tagsService = tagsService;
    }

    @RequestMapping(value = ServerApi.TAG_PATH + "/images", method = RequestMethod.GET)
    public ResponseEntity<List<Image>> getImagesByTag(@PathVariable("id") int id) {
        if (tagsService.getTag(id) == null) {
            return new ResponseEntity<List<Image>>(HttpStatus.NOT_FOUND);
        }
        List<Image> images = imagesService.getImagesByTag(id);
        if ( images.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(images,HttpStatus.OK);
    }
}
