package br.com.mesttra.person.mapper.custom;

import br.com.mesttra.person.data.vo.v2.PersonVOV2;
import br.com.mesttra.person.entity.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVOV2(Person person) {
        PersonVOV2 personVOV2 = new PersonVOV2();
        personVOV2.setId(person.getId());
        personVOV2.setFirstName(person.getFirstName());
        personVOV2.setLastName(person.getLastName());
        personVOV2.setAddress(person.getAddress());
        personVOV2.setGender(person.getGender());
        personVOV2.setBirthDate(new Date());
        return personVOV2;
    }

    public Person convertVOV2ToEntity(PersonVOV2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        //entity.setBirthDate(new Date());
        return entity;
    }

}
