package test.gedz.controler;

/**
 * Created by Gedz on 16-Jun-18.
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import test.gedz.entity.Person;
import test.gedz.service.PersonService;


@RestController
public class PersonController {

    @Autowired
    PersonService personService;  //Service which will do all data retrieval/manipulation work


    //-------------------Retrieve All Persons--------------------------------------------------------

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> listAllPersons() {
        List<Person> Persons = personService.findAllPersons();
        if (Persons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(Persons, HttpStatus.OK);
    }


    //-------------------Retrieve Single Person--------------------------------------------------------

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable("id") int id) {
        System.out.println("Fetching Person with id " + id);
        Person person = personService.findById(id);
        if (person == null) {
            System.out.println("Person with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }


    //-------------------Create a Person--------------------------------------------------------

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public ResponseEntity<Void> createPerson(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Person " + person.getName());

        if (personService.createPerson(person) == null) {
            System.out.println("A Person with name " + person.getName() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Person/{id}").buildAndExpand(person.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a Person --------------------------------------------------------

    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
        System.out.println("Updating Person " + id);

        Person currentPerson = personService.updatePerson(id, person);

        if (currentPerson == null) {
            System.out.println("Person with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(currentPerson, HttpStatus.OK);
    }

    //------------------- Delete a Person --------------------------------------------------------

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deletePerson(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Person with id " + id);

        Person person = personService.findById(id);
        if (person == null) {
            System.out.println("Unable to delete. Person with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        personService.deletePersonById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/person", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deleteAllPersons() {
        System.out.println("Deleting All Persons");

        personService.deleteAllPersons();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
