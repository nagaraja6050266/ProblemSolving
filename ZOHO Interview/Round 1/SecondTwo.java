
class Second {

    public static void main(String[] args) {
        int[][] input = {{1, 2, 3, 41}, {4, 5, 6, 45}, {7, 8, 9, 43}, {5, 7, 2, 89}};
        int n = input.length;

        int[][] result = new int[n][n];

        //int x = 0, y = n - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - i - 1] = input[i][j];
            }
        }

        System.out.println("Initial Array");
        printArray(input);

        System.out.println("Rotated Array");
        printArray(result);

    }

    private static void printArray(int[][] input) {
        int n = input.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }
}
