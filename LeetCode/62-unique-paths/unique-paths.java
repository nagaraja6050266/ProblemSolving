class Solution {
    public int uniquePaths(int m, int n) {
        int max=Math.max(m,n)-1;
        int min=Math.min(m,n)-1;
        double result=1;
        for(int i=m+n-2;i>max;i--){
            result*=i;
            if(min>1){
                result/=min--;
            }
            System.out.println(result);
        }
        return (int)(Math.round(result));
    }
}