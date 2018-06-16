package test.gedz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.gedz.entity.Person;
import test.gedz.repository.PersonRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Gedz on 16-Jun-18.
 */
@Transactional
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    public Person findById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    public boolean isPersonExist(Person person) {
        return personRepository.existsByName(person.getName());
    }

    public Person createPerson(Person person) {
        if (isPersonExist(person)) return null;
        return personRepository.save(person);
    }

    public Person updatePerson(int id, Person person) {
        return personRepository.findById(id)
                .map(currentPerson -> copyProperties(person, currentPerson))
                .map(personRepository::save)
                .orElse(null);
    }

    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }

    public void deleteAllPersons() {
        personRepository.deleteAll();
    }

    private Person copyProperties(Person person, Person currentPerson) {
        currentPerson.setName(person.getName());
        currentPerson.setBirthday(person.getBirthday());
        return currentPerson;
    }
}
