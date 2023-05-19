import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws MyException, IOException {

        // считать строку
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        // получить из неё 2 числа и операнд
        int numElements = tokenizer.countTokens();

        String value_1 = tokenizer.nextToken();
        String operand = tokenizer.nextToken();
        String value_2 = tokenizer.nextToken();

        String answer = getAnswer(value_1,operand,value_2, numElements);

        System.out.println(answer);
    }

    private static String getAnswer(String v1, String operand,String v2, int n) throws MyException {

        if(n != 3) {
            throw new MyException("EXCEPTION: wrong input");
        }

        int result;

        // check if val_1, val_2 are roman
        if(Roman.isPresent(v1)) {
            if(!Roman.isPresent(v2)) {
                throw new MyException("Second value is incorrect");
            }

            switch (operand) {
                case "+" -> result = (Roman.getValue(Roman.valueOf(v1)) + Roman.getValue(Roman.valueOf(v2)));
                case "*" -> result = (Roman.getValue(Roman.valueOf(v1)) * Roman.getValue(Roman.valueOf(v2)));
                case "-" -> {
                    if (Roman.getValue(Roman.valueOf(v1)) <= Roman.getValue(Roman.valueOf(v2))) {
                        throw new MyException("Roman only have numbers higher than zero") ;
                    }
                    result = (Roman.getValue(Roman.valueOf(v1)) - Roman.getValue(Roman.valueOf(v2)));
                }
                case "/" -> {
                    if (Roman.getValue(Roman.valueOf(v1)) < Roman.getValue(Roman.valueOf(v2))) {
                        throw new MyException("Roman only have numbers higher than zero") ;
                    }
                    result = (Roman.getValue(Roman.valueOf(v1)) / Roman.getValue(Roman.valueOf(v2)));
                }
                default -> throw new MyException("Wrong operand") ;
            }
            return itor(result);
        }
        // check if val_1, val_2 are integer

        int a = stoi(v1);
        int b = stoi(v2);

        if(a != 42) { // 42 is the error of converting String to int
            if(b == 42) {
                throw new MyException("Second value is incorrect");
            }

            switch (operand) {
                case "+" -> result = a + b;
                case "*" -> result = a * b;
                case "-" -> result = a - b;
                case "/" -> result = a / b;
                default -> throw new MyException("Wrong operand");

            }
            return String.valueOf(result);
        }
        return "Unexpected error";
    }

    private static int stoi(String str) {
        try {
            int x = Integer.parseInt(str);
            if(1 <= x && x <= 10) return x;
            else return 42;
        } catch (NumberFormatException e) {
            return 42;
        }
    }

    private static String itor(int n) {
        String[] ones = {"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};

        if(n == 100) {
            return "C";
        }

        return tens[n/10]+ones[n % 10];
    }
    private enum Roman {
        I(1), II(2), III(3), IV(4), V(5),
        VI(6), VII(7), VIII(8), IX(9), X(10);

        private final int value;
        Roman(int value) {
            this.value = value;
        }

        public static int getValue(Roman number) {
            return number.value;
        }

        public static boolean isPresent(String number) {

            try {
                Enum.valueOf(Roman.class, number);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

    }
}

