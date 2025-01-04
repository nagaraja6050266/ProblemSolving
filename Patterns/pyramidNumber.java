public class pyramidNumber {
    public static void main(String[] args) {

//		      1
//		    1 2 1
//		  1 2 3 2 1
//		1 2 3 4 3 2 1
        int n = 5;
        int left = n + 1, right = n + 1;
        for (int i = 1; i <= n; i++) {
            int c = i;
            for (int j = 1; j <= 2 * n; j++) {
                if (j >= left && j <= right) {
                    System.out.print(c + " ");
                    if (j <= n) {
                        c--;
                    } else {
                        c++;
                    }
                } else {
                    System.out.print("  ");
                }
            }
            left--;
            right++;
            System.out.println();
        }
    }
}
