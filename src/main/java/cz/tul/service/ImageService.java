package cz.tul.service;

import cz.tul.data.Image;
import cz.tul.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Martin on 30.04.2017.
 */
@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void create (Image image) { imageRepository.save(image); }

    public List<Image> getAllImages() {
        return StreamSupport.stream(imageRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Image getImage(int id) {
        return imageRepository.findOne(id);
    }

    public void update(Image image) {
        image.setDateEdit(new Date());
        imageRepository.save(image);
    }

    public void deleteImages() {
        imageRepository.deleteAll();
    }
}
