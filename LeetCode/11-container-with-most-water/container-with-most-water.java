class Solution {
    public int maxArea(int[] height) {
        int result=0,left=0,right=height.length-1;
        while(left<right){
            int currArea=(Math.min(height[left],height[right]))*(right-left);
            result=Math.max(result,currArea);
            if(height[left]>height[right]){
                right--;
            }
            else{
                left++;
            }
        }
        return result;
    }
}