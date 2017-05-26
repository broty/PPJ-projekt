package cz.tul.repositories;

import cz.tul.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Martin on 30.04.2017.
 */

//@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends CrudRepository<User, Integer>{
    List<User> findByName(String name);
    User findById(int id);
}
