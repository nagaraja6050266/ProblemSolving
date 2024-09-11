
class RomanToInteger {

    static int[] romanValues = {1, 5, 10, 50, 100, 500, 1000};
    static int[] count = {3, 1, 3, 1, 3, 1, 3};
    static String roman = "IVXLCDM";

    public static void main(String[] args) {

        //Input
        String input = "VIII";

        if (!valid(input)) {
            System.out.println("Conditions violated");
            return;
        }

        int currRomanValue = romanValues[roman.indexOf(input.charAt(input.length() - 1))];
        int sum = currRomanValue;

        for (int i = input.length() - 1; i > 0; i--) {
            currRomanValue = romanValues[roman.indexOf(input.charAt(i))];
            int prevRomanValue = romanValues[roman.indexOf(input.charAt(i - 1))];
            if (currRomanValue <= prevRomanValue) {
                sum += prevRomanValue;
            } else {
                sum -= prevRomanValue;
            }
        }

        //Output
        System.out.print(sum);
        
    }

    private static boolean valid(String input) {
        int currCount = 1;
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                currCount++;
            }
            int currIndex = roman.indexOf(input.charAt(i));
            if (currCount > count[currIndex]) {
                return false;
            }
        }
        return true;
    }
}
