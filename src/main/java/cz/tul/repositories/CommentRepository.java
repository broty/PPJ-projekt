package cz.tul.repositories;

import cz.tul.data.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Martin on 30.04.2017.
 */
//@Repository
@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    @Query("UPDATE Comment set likes = likes + 1 WHERE id = :id")
    public void incrementLikes(@Param("id") int id);

    @Query("UPDATE Comment set likes = likes - 1 WHERE id = :id")
    public void decrementLikes(@Param("id") int id);
}
