package br.com.mesttra.calculadora.service;

public class CalculadoraService {

    public static double sum(String numberOne, String numberTwo) {
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    public static double sub(String numberOne, String numberTwo) {
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    public static double div(String numberOne, String numberTwo) {
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    public static double mult(String numberOne, String numberTwo) {
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    public static double sqrt(String numberOne) {
        return Math.sqrt(convertToDouble(numberOne));
    }

    public static double avg(String numberOne, String numberTwo) {
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }


    private static double convertToDouble(String numberStr) {
        if (numberStr == null) return 0D;
        String number = numberStr.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private static boolean isNumeric(String numberStr) {

        if (numberStr == null) {
            return false;
        }
        String number = numberStr.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");

    }



}
