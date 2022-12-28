package br.com.mesttra.person.service;

import br.com.mesttra.person.entity.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {

        logger.info("Encontrando todas as pessoas!");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;

    }

    public Person findById(String id) {

        logger.info("Encontrando uma pessoa!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Eduardo");
        person.setLastName("Floriano");
        person.setAddress("São josé dos campos");
        person.setGender("Homem");
        return person;
    }

    private Person mockPerson(int i) {

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person Name " + i);
        person.setLastName("Last Name " + i);
        person.setAddress("Some Address in Brasil " + i);
        person.setGender("Homem");
        return person;

    }

    public Person createPerson(Person person) {

        logger.info("Criando uma pessoa!");

        return person;
    }

    public Person updatePerson(Person person) {

        logger.info("Atualizando uma pessoa!");

        return person;
    }

    public void delete(String id) {

        logger.info("Removendo uma pessoa!");

    }

}
