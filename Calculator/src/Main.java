import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static String input;

    static {
        System.out.println("Введите число от 1 до 10 (арабскими или римскими цифрами), операнд (+,-,*,/), второе число:");
        BufferedReader fr = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = fr.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println(calc(input));
    }
    public static String calc(String input) {

        String[] operandsAndOperatorArr = input.split("(?<=[+\\-*/])|(?=[+\\-*/])");

        if (!(operandsAndOperatorArr.length == 3)) {
            throw new IllegalArgumentException("Введенная строка не соответсвует условию");
        }

        String operator = operandsAndOperatorArr[1];
        String operand1 = operandsAndOperatorArr[0].replaceAll("\s*","");
        String operand2 = operandsAndOperatorArr[2].replaceAll("\s*","");
        String operand1and2 = operand1 + operand2;

        if (!operator.matches("[\\-*/+]")) {
            throw new IllegalArgumentException("Введен неверный оператор");
        }
        if (!operand1and2.matches("[\\dIVX]+")) {
            throw new IllegalArgumentException("Введено неверное значение операнда");
        }
        if (!operand1and2.matches("(\\d+)|([IXV]+)")) {
            throw new IllegalArgumentException("Операнды имеют разный тип");
        }

        boolean isRoman = false;
        int op1;
        int op2;

        if (operand1and2.matches("\\d+")) {
            op1 = Integer.parseInt(operand1);
            op2 = Integer.parseInt(operand2);
        } else {
            isRoman = true;
            op1 = Converter.romanToArabic(operand1);
            op2 = Converter.romanToArabic(operand2);
        }
        if ((op1<=0||op1>10)||(op2<=0||op2>10)){
            throw new IllegalArgumentException("Введенное число меньше 1 или больше 10");
        }

        int resultInt=0;

        switch (operator) {
            case "+" -> resultInt = Operations.add(op1, op2);
            case "-" -> resultInt = Operations.subtract(op1, op2);
            case "*" -> resultInt = Operations.multiply(op1, op2);
            case "/" -> resultInt = Operations.divide(op1, op2);
        }

        String result;

        if (isRoman) {
            result = Converter.arabicToRoman(resultInt);
        } else result = String.valueOf(resultInt);

        return result;
    }
}
