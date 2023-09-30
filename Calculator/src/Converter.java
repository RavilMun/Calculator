import java.util.HashMap;

class Converter {

    public static int romanToArabic(String romanNum) {
        char[] romanNumArray = romanNum.toCharArray();
        int result = 0;
        int valueSymbol = 0;
        int valuePrev = 0;

        for (int i = romanNumArray.length - 1; i >= 0; i--) {

            switch (romanNumArray[i]) {
                case 'I' -> valueSymbol = 1;
                case 'V' -> valueSymbol = 5;
                case 'X' -> valueSymbol = 10;
                case 'L' -> valueSymbol = 50;
                case 'C' -> valueSymbol = 100;
            }

            if (valueSymbol >= valuePrev) {
                result += valueSymbol;
            } else {
                result -= valueSymbol;
            }
            valuePrev = valueSymbol;
        }
        return result;
    }

    public static String arabicToRoman(int arabicNum) {

        if (arabicNum <= 0) {
            throw new IllegalArgumentException("Римские числа могут быть только натуральными");
        }

        HashMap<Integer, String> arabicKeyMap = new HashMap<>();

        arabicKeyMap.put(1, "I");
        arabicKeyMap.put(4, "IV");
        arabicKeyMap.put(5, "V");
        arabicKeyMap.put(9, "IX");
        arabicKeyMap.put(10, "X");
        arabicKeyMap.put(40, "XL");
        arabicKeyMap.put(50, "L");
        arabicKeyMap.put(90, "XC");
        arabicKeyMap.put(100, "C");

        int[] keys = {1, 4, 5, 9, 10, 40, 50, 90, 100};

        String roman = "";
        int interNum = arabicNum;

        while (interNum > 0) {
            for (int i = 0; i < keys.length; i++) {
                if (interNum == keys[i]) {
                    roman += arabicKeyMap.get(keys[i]);
                    interNum -= keys[i];
                    break;
                } else if ((interNum - keys[i]) < 0) {
                    roman += arabicKeyMap.get(keys[i - 1]);
                    interNum = interNum - keys[i - 1];
                    break;
                }
            }
        }
        return roman;
    }
}

