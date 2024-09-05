
import java.util.Arrays;

class SplitEvenOdd {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] result = new int[2][array.length];
        splitArray(result,array);
        System.out.println(Arrays.toString(result[0])+"\n"+Arrays.toString(result[1]));
    }


    private static void splitArray(int[][] result,int[] array){
        int odd = 0, even = 0;
        for (int i : array) {
            if (i % 2 != 0) {
                result[0][odd++] = i;
            } else {
                result[1][even++] = i;
            }
        }
    }
    
}
