package br.com.mesttra.calculadora.controller;

import br.com.mesttra.calculadora.exceptions.UnsuporttedMathOperationException;
import br.com.mesttra.calculadora.service.CalculadoraService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class MathController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuporttedMathOperationException("Envie um valor numérico!");
        }
        return CalculadoraService.sum(numberOne, numberTwo);

    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double sub(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuporttedMathOperationException("Envie um valor numérico!");
        }
        return CalculadoraService.sub(numberOne, numberTwo);

    }

    @RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double mult(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuporttedMathOperationException("Envie um valor numérico!");
        }
        return CalculadoraService.mult(numberOne, numberTwo);

    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double div(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuporttedMathOperationException("Envie um valor numérico!");
        }
        return CalculadoraService.div(numberOne, numberTwo);

    }

    @RequestMapping(value = "/avg/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double avg(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsuporttedMathOperationException("Envie um valor numérico!");
        }
        return CalculadoraService.avg(numberOne, numberTwo);

    }

    @RequestMapping(value = "/sqrt/{numberOne}", method = RequestMethod.GET)
    public double sqrt(
            @PathVariable(value = "numberOne") String numberOne
    ) throws Exception {

        if (!isNumeric(numberOne)) {
            throw new UnsuporttedMathOperationException("Envie um valor numérico!");
        }
        return CalculadoraService.sqrt(numberOne);

    }




    private boolean isNumeric(String numberStr) {

        if (numberStr == null) {
            return false;
        }
        String number = numberStr.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");

    }

}
