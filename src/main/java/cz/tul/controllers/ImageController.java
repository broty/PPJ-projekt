package cz.tul.controllers;

import cz.tul.client.FileManager;
import cz.tul.client.ImageStatus;
import cz.tul.client.ServerApi;
import cz.tul.data.Image;
import cz.tul.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Martin on 26.05.2017.
 */

@RestController
public class ImageController {
    private ImageService imagesService;
    @Autowired
    public void setImagesService(ImageService imagesService) {
        this.imagesService = imagesService;
    }

    @RequestMapping(value = ServerApi.IMAGES_PATH + "/getByName", method = RequestMethod.GET)
    public ResponseEntity<List<Image>> getImagesByName(@RequestParam("name") String name) {
        List<Image> images = imagesService.getImagesByName(name);
        if ( images.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(images,HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH + "/like", method = RequestMethod.GET)
    public ResponseEntity<Image> likeImage(@PathVariable("id") int id) {
        if(imagesService.getImage(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        imagesService.like(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.IMAGE_PATH + "/dislike", method = RequestMethod.GET)
    public ResponseEntity<Image> dislikeImage(@PathVariable("id") int id) {
        if(imagesService.getImage(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        imagesService.dislike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private FileManager imageDataMgr;

    @RequestMapping(value = ServerApi.UPLOAD_PATH, method = RequestMethod.POST)
    public
    @ResponseBody
    ImageStatus uploadImage(@PathVariable("name") String name,
                            @RequestParam("data") MultipartFile imageData,
                            HttpServletResponse response) {

        ImageStatus state = new ImageStatus(ImageStatus.ImageState.READY);

        setFileManager();

        try {
            imageDataMgr.saveImageData(name, imageData.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return state;
    }

    @RequestMapping(value = ServerApi.DOWNLOAD_PATH, method = RequestMethod.GET)
    public
    @ResponseBody
    HttpEntity<byte[]> downloadImage(@PathVariable("name") String name,
                                     HttpServletResponse response) {

        byte[] image = new byte[0];
        HttpHeaders headers = new HttpHeaders();

        setFileManager();
        if (imageDataMgr.imageExists(name)) {
            try {

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                imageDataMgr.copyImageData(name, bos);
                image = bos.toByteArray();
                headers.setContentLength(image.length);
                String mime = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));
                headers.setContentType(MediaType.valueOf(mime)); //or what ever type it is
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return new HttpEntity<byte[]>(image, headers);
    }

    public void setFileManager() {

        try {

            imageDataMgr = FileManager.get();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

}
