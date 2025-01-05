class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
      
        while (left<right){
            int t = numbers[left] + numbers[right];
            if(t==target){
                return new int[]{left + 1, right + 1};
            }
            else if (t>target){
                right--;
            }
            else{
                left++;
            }
        }
        return new int[]{-1,-1};
    }
}