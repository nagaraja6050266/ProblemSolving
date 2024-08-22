class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max=0,count=0;
        for(int i:nums){
            if(i==1){
                count+=1;
                max=Math.max(max,count);
            }
            else{
                count=0;
            }
        }
        return max;
    }
}