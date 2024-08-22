class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n=matrix.length;
        int start=0,stop=n-1,mid;
        while(start<=stop){
            mid=(start+stop)/2;
            if(matrix[mid][0]==target){
                return true;
            }
            else if(matrix[mid][0]>target){
                stop=mid-1;
            }
            else{
                start=mid+1;
            }
        }
        int row=(start+stop)/2;
        start=0;
        stop=matrix[0].length-1;
        while(start<=stop){
            mid=(start+stop)/2;
            if(matrix[row][mid]==target){
                return true;
            }
            else if(matrix[row][mid]>target){
                stop=mid-1;
            }
            else{
                start=mid+1;
            }
        }
        return false;
    }
}