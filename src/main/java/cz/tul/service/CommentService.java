package cz.tul.service;

import com.google.common.collect.Lists;
import cz.tul.data.Comment;
import cz.tul.repositories.CommentRepository;
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
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void create (Comment comment) { commentRepository.save(comment); }

    public List<Comment> getAllComments() {
        return Lists.newArrayList(commentRepository.findAll());
    }

    public Comment getComment(int id) {
        return commentRepository.findOne(id);
    }

    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    public void update(Comment comment, boolean updateDateEdit) {
        if (updateDateEdit) {
            comment.setDateEdit(new Date());
        }
        update(comment);
    }

    public void deleteComments() {
        commentRepository.deleteAll();
    }

    public void like (int id) { commentRepository.incrementLikes(id); };
    public void dislike (int id) { commentRepository.decrementLikes(id); };
}
