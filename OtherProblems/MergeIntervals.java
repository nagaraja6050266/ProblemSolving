package OtherProblems;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        int c = 0;
        List<List<Integer>> arr = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        //Array to ArrayList
        for (int[] i : intervals) {
            arr.add(Arrays.asList(i));
        }

        int[][] output = new int[intervals.length][2];
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).get(1) >= arr.get(i + 1).get(0)) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(arr.get(i).get(0));
                temp.add(((arr.get(i).get(1)) < (arr.get(i + 1).get(1))) ? (arr.get(i + 1).get(1)) : (arr.get(i).get(1)));
                arr.remove(i);
                arr.remove(i);
                arr.add(temp);
            }
        }
        return arr.toArray();
    }
}

