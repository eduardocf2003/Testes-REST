package br.com.mesttra.person.service;

import br.com.mesttra.person.data.vo.v1.PersonVO;
import br.com.mesttra.person.data.vo.v2.PersonVOV2;
import br.com.mesttra.person.entity.Person;
import br.com.mesttra.person.exceptions.ResourceNotFoundException;
import br.com.mesttra.person.mapper.DozerMapper;
import br.com.mesttra.person.mapper.custom.PersonMapper;
import br.com.mesttra.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {

        logger.info("Encontrando todas as pessoas!");


        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);

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

        return DozerMapper.parseObject(entity, PersonVO.class);
    }


    public PersonVO createPerson(PersonVO person) {

        logger.info("Criando uma pessoa!");

        var entity = DozerMapper.parseObject(person, Person.class);

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

        return vo;


    }

    public PersonVO updatePerson(PersonVO person) {

        logger.info("Atualizando uma pessoa!");

        var entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sem registro para o id: " + person.getId()));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

        return vo;
    }

    public void delete(Long id) {

        logger.info("Removendo uma pessoa!");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registro para o id: " + id));

        personRepository.delete(entity);

    }

    public PersonVOV2 createPersonV2(PersonVOV2 person) {

        logger.info("Criando uma pessoa com V2!");

        var entity = mapper.convertVOV2ToEntity(person);

        var vo = mapper.convertEntityToVOV2(personRepository.save(entity));

        return vo;


    }

}
