
class diamondBorder {

    public static void main(String[] args) {
        int n = 6, left = n, right = left;
        for (int i = 1; i < 2 * n; i++) {
            for (int j = 1; j <= 2 * n - 1; j++) {
                if (j == right || j == left) {
                    System.out.print('*');
                } else {
                    System.out.print(' ');
                }
            }
            if (i < n) {
                left--;
                right++;
            } else {
                left++;
                right--;
            }
            System.out.println();
        }
    }
}
