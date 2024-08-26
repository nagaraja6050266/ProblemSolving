class Solution {
    public void rotate(int[][] matrix) {
        //Worst approach
        int n=matrix.length;
        int[][] result=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                result[i][j]=matrix[n-j-1][i];
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=result[i][j];
            }
        }
    }
}