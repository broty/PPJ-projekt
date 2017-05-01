package cz.tul.service;

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
        return StreamSupport.stream(commentRepository.findAll().spliterator(),false).collect(Collectors.toList());
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
}
