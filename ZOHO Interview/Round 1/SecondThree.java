class SecondThree {
    public static void main(String[] args) {
        int[][] input = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int n = input.length;

        //Display Initial Array
        System.out.println("Initial Matrix: ");
        printArray(input);

        //Finding Transpose
        transpose(input, n);

        //Reversing Each array
        for (int i = 0; i < n; i++) {
            reverse(input[i]);
        }

        //Display rotated Array
        System.out.println("After rotation");
        printArray(input);
    }

    private static void swapInTwoD(int[][] input, int i, int j, int m, int n) {
        int temp = input[i][j];
        input[i][j] = input[m][n];
        input[m][n] = temp;
    }

    private static void swapInOneD(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    private static void reverse(int[] input) {
        int l = 0, r = input.length - 1;
        while (l < r) {
            swapInOneD(input, l++, r--);
        }
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

    private static void transpose(int[][] input, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                swapInTwoD(input, i, j, j, i);
            }
        }
    }
}
