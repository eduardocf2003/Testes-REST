package br.com.mesttra.person.service;

import br.com.mesttra.person.entity.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

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

}
