package test.gedz.repository;

import org.springframework.data.repository.CrudRepository;
import test.gedz.entity.Person;

/**
 * Created by Gedz on 16-Jun-18.
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
