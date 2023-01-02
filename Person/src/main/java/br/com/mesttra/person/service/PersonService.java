package br.com.mesttra.person.service;

import br.com.mesttra.person.controller.PersonController;
import br.com.mesttra.person.data.vo.v1.PersonVO;
import br.com.mesttra.person.entity.Person;
import br.com.mesttra.person.exceptions.ResourceNotFoundException;
import br.com.mesttra.person.mapper.DozerMapper;
import br.com.mesttra.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<PersonVO> findAll() {

        logger.info("Encontrando todas as pessoas!");


        var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;

    }

    public PersonVO findById(Long id) {

        logger.info("Encontrando uma pessoa!");

        PersonVO person = new PersonVO();
        person.setFirstName("Eduardo");
        person.setLastName("Floriano");
        person.setAddress("São josé dos campos");
        person.setGender("Homem");
       var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registro para o id: " + id));

        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }


    public PersonVO createPerson(PersonVO person) {

        logger.info("Criando uma pessoa!");

        var entity = DozerMapper.parseObject(person, Person.class);

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;


    }

    public PersonVO updatePerson(PersonVO person) {

        logger.info("Atualizando uma pessoa!");

        var entity = personRepository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Sem registro para o id: " + person.getKey()));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {

        logger.info("Removendo uma pessoa!");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registro para o id: " + id));

        personRepository.delete(entity);

    }

}
