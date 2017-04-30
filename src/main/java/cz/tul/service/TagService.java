package cz.tul.service;

import cz.tul.data.Tag;
import cz.tul.repositories.TagRepository;
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
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public void create (Tag tag) { tagRepository.save(tag); }

    public List<Tag> getAllTags() {
        return StreamSupport.stream(tagRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Tag getTag(int id) {
        return tagRepository.findOne(id);
    }

    public void deleteTags() {
        tagRepository.deleteAll();
    }
}
