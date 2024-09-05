
import java.util.Arrays;

class NumbertoBD {

    public static void main(String[] args) {
        int inputNumber = 546;
        convertToBinary(inputNumber);
    }

    private static void convertToBinary(int inputNumber) {
        int[] binaryArray = new int[9];
        int value = 1;
        while (inputNumber > 0) {
            int digit = inputNumber % 10;
            addToBinaryArray(binaryArray, digit, value);
            value *= 10;
            inputNumber /= 10;
        }
        System.out.println(Arrays.toString(binaryArray));
    }

    private static void addToBinaryArray(int[] binaryArray, int digit, int value) {
        for (int i = 0; i < digit; i++) {
            binaryArray[i] += value;
        }
    }

}
