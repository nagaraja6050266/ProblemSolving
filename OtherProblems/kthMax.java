

class kthMax {

    public static void main(String[] args) {
        int[] array = {1, 45, 6, 7, 48, 3, 5, 7, 342, 678, 3, 67, 24};
        int k = 5;
        bubbleSortUpto(array, k);
        // System.out.println(Arrays.toString(array));
        System.out.println("K th Max is " + array[array.length - k]);
    }

    private static void bubbleSortUpto(int[] array, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] array, int j, int i) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
