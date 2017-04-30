package cz.tul.repositories;

import cz.tul.data.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Martin on 30.04.2017.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
