
class oddEven {

    public static void main(String[] args) {
        int n = 10, c = 1, lastDigit = 0, firstDigit = 1;
        for (int i = 1; i <= n; i++) {
            boolean flag = true;
            for (int j = 1; j <= i; j++) {
                System.out.print(c + " ");
                if (flag && c > lastDigit) {
                    flag = false;
                    firstDigit = c + 1;
                }
                c += 2;
            }
            lastDigit = c - 2;
            c = firstDigit;
            System.out.println();
        }
    }
}
