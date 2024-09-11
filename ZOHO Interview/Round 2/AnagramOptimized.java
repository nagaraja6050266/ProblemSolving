
class AnagramOptimized {

    public static void main(String[] args) {
        String a = "listen6&6";
        String b = "s66ile&nt";

        if (a.length() != b.length()) {
            System.out.println("Not a valid Anagram");
            return;
        }

        int m = a.length();
        int[] map = new int[26 + 10 + 30];

        for (int i = 0; i < m; i++) {
            int index = getIndex(a.charAt(i));
            map[index] += 1;
        }

        for (int i = 0; i < m; i++) {
            int index = getIndex(b.charAt(i));
            map[index] -= 1;
        }

        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                System.err.println("Not a valid Anagram");
                return;
            }
        }

        System.out.println("Valid Anagram");

    }

    private static int getIndex(char c) {
        if (Character.isAlphabetic(c)) {
            return c - 'a';
        } else if (Character.isDigit(c)) {
            return c - '0' + 26;
        } else {
            return c - '.' + 26 + 10;
        }
    }
}
