package cz.tul.repositories;

import cz.tul.data.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Martin on 30.04.2017.
 */

//@Repository
@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
public interface TagRepository extends CrudRepository<Tag, Integer>{
}
