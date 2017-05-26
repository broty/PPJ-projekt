package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Comment;
import cz.tul.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Martin on 26.05.2017.
 */

@RestController
public class CommentController {
    private CommentService commentsService;

    @Autowired
    public void setImagesService(CommentService commentsService) {
        this.commentsService = commentsService;
    }

    @RequestMapping(value = ServerApi.COMMENT_PATH + "/like", method = RequestMethod.GET)
    public ResponseEntity<Comment> likeComment(@PathVariable("id") int id) {
        if(commentsService.getComment(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentsService.like(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.COMMENT_PATH + "/dislike", method = RequestMethod.GET)
    public ResponseEntity<Comment> dislikeComment(@PathVariable("id") int id) {
        if(commentsService.getComment(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentsService.like(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
