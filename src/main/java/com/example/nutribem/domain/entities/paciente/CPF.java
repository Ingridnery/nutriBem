package com.example.nutribem.domain.entities.paciente;

import java.util.Objects;

public class CPF {
    private final static Integer CPF_LENGTH = 11;
    private final String number;

    private CPF(String cpfNumber) {
        this.number = cpfNumber;
    }

    public static CPF valueOf(String cpfNumber) {
        if (!isValid(cpfNumber)) throw new IllegalArgumentException("Cpf is invalid");
        return new CPF(cpfNumber.replace(".", ""));
    }

    public static CPF valueOf(Long cpfNumber) {
        return valueOf(String.valueOf(cpfNumber));
    }

    public static Boolean isValid(String cpfNumber) {
        if (cpfNumber == null) return false;

        String cpf = cpfNumber
                .replace(".", "")
                .replace("-", "")
                .trim();

        if (cpf.length() != CPF_LENGTH) return false;
        if (cpf.matches("(.)\\1*")) return false; // If all digits are the same
        if (!cpf.matches("\\d+")) return false; // If there isn't only digits

        // Calculates the expected value of the first digit and
        // checks if the calculated value matches the provided digit
        int firstDigitCalc = calcDigit(cpf.substring(0, 9));
        int firstDigitInCpf = Integer.parseInt(cpf.substring(9, 10));
        if (firstDigitInCpf != firstDigitCalc) return false;

        // Calculates the expected value of the second digit and
        // checks if the calculated value matches the provided digit
        int secondDigitCalc = calcDigit(cpf.substring(0, 9) + firstDigitCalc);
        int secondDigitInCpf = Integer.parseInt(cpf.substring(10));
        return secondDigitInCpf == secondDigitCalc;
    }

    private static Integer calcDigit(String cpf) {
        double sum = 0.0;

        int length = cpf.length();
        for (int i = 0; i < length; i++) {
            int charValue = Integer.parseInt(
                    cpf.substring(i, i + 1)
            );
            sum += ((length + 1) - i) * charValue;
        }

        double rest = sum % 11;

        return (int) (rest >= 2 ?
                11 - (sum % 11) :
                0
        );
    }

    public String getCpf() {
        return number;
    }

    public String getCpfFormatted() {
        StringBuilder builder = new StringBuilder(number);
        builder.insert(3, ".");
        builder.insert(7, ".");
        builder.insert(11, "-");

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (CPF) o;
        return getCpf().equals(cpf.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf());
    }
}
