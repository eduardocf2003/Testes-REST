package br.com.mesttra.person.controller;

import br.com.mesttra.person.entity.Person;
import br.com.mesttra.person.exceptions.UnsuporttedMathOperationException;
import br.com.mesttra.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;


    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(
            @PathVariable(value = "id") String id) throws Exception {


        return personService.findById(id);

    }



}
