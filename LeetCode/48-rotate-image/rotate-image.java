class Solution {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                swap(matrix,i,j,j,i);
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n/2;j++){
                swap(matrix,i,j,i,n-j-1);
            }
        }
    }
    public static void swap(int[][] matrix,int i,int j,int a,int b){
        int temp=matrix[a][b];
        matrix[a][b]=matrix[i][j];
        matrix[i][j]=temp;
    }
}