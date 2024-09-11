
class AnagramOptimized2 {

    static String a = "listenl6&6";
    static String b = "s66ille&nt";

    public static void main(String[] args) {

        if (a.length() != b.length()) {
            System.out.println("Not a valid Anagram");
            return;
        }

        int m = a.length();
        int[] map = new int[m];

        for (int i = 0; i < m; i++) {
            int index = a.indexOf(a.charAt(i));
            map[index] += 1;
        }

        for (int i = 0; i < m; i++) {
            int index = a.indexOf(b.charAt(i));
            if (index == -1) {
                System.out.print("Not a valid Anagram");
                return;
            }
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
}
