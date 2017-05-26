package cz.tul.service;

import com.google.common.collect.Lists;
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
        return Lists.newArrayList(imageRepository.findAll());
    }

    public Image getImage(int id) {
        return imageRepository.findOne(id);
    }

    public void update(Image image) {
        imageRepository.save(image);
    }

    public void update(Image image, boolean updateDateEdit) {
        if (updateDateEdit) {
            image.setDateEdit(new Date());
        }
        update(image);
    }

    public List<Image> getUserImages(int id) {
        return Lists.newArrayList(imageRepository.findByUserId(id));
    }

    public void deleteImages() {
        imageRepository.deleteAll();
    }

    public List<Image> getImagesByName(String name) {
        return Lists.newArrayList(imageRepository.findByName(name));
    }

    public List<Image> getImagesByTag(int id) {
        return Lists.newArrayList(imageRepository.findByTags(id));
    }

    public void like (int id) { imageRepository.incrementLikes(id); };
    public void dislike (int id) { imageRepository.decrementLikes(id); };
}
