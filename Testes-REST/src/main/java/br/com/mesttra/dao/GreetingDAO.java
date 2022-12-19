package br.com.mesttra.dao;

import br.com.mesttra.entity.Greeting;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingDAO {

    private static final String template = "Hello, %s!";

    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(0, String.format(template, name));
    }

}
