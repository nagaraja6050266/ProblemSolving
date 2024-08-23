class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        while(j<n){
            nums1[m++]=nums2[j++];
        }
        Arrays.sort(nums1);
    }
}