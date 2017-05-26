package cz.tul.repositories;

import cz.tul.data.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Martin on 30.04.2017.
 */

//@@Repository
@RepositoryRestResource(collectionResourceRel = "images", path = "images")
public interface ImageRepository extends CrudRepository<Image, Integer> {
    List<Image> findByUserId(int id);
    List<Image> findByName(String name);
    List<Image> findByTags(int id);

    @Query("UPDATE Image set likes = likes + 1 WHERE id = :id")
    public void incrementLikes(@Param("id") int id);

    @Query("UPDATE Image set likes = likes - 1 WHERE id = :id")
    public void decrementLikes(@Param("id") int id);
}
