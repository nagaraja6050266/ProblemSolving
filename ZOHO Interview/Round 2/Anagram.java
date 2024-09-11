
class Anagram {

    public static void main(String[] args) {
        String a = "listen";
        String b = "silent";

        if (a.length() != b.length()) {
            System.out.println("Not a valid Anagram");
            return;
        }

        int m = a.length();

        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (aArray[i] == bArray[j]) {
                    bArray[j] = '\t';
                }
            }
        }

        for (char c : bArray) {
            if (c != '\t') {
                System.out.println("Not a valid Anagram");
                return;
            }
        }

        System.out.println("Valid Anagram");

    }
}
