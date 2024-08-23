class Solution {
    public int minimumOperations(int[] nums) {
        int result=0;
        for(int i:nums){
            if(i%3!=0){
                result++;
            }
        }
        return result;
    }
}