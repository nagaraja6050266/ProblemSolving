
import java.util.Arrays;

class MergeIntervals {

    public static void main(String[] args) {
        int[][] input = {{2, 4}, {6, 9}, {1, 7}, {4, 7}};
        // int[][] input = {{1,3},{2,4},{6,8},{9,10}};
        sortInput(input);
        int flag = 0;

        for (int i = 0; i < input.length - 1; i++) {
            if (input[i][1] > input[i + 1][0]) {
                if (input[i][1] >= input[i + 1][1]) {
                    swap(input, i, i + 1);
                } else {
                    input[i + 1][0] = input[i][0];
                }
                flag++;
            }
        }

        //display
        for (int i = flag; i < input.length; i++) {
            System.out.print(Arrays.toString(input[i]));
        }

    }

    private static void sortInput(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length - 1; j++) {
                if (input[j][0] > input[j + 1][0]) {
                    swap(input, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[][] input, int m, int n) {
        int temp[] = input[m];
        input[m] = input[n];
        input[n] = temp;
    }

}
