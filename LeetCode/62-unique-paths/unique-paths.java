class Solution {
    public int uniquePaths(int m, int n) {
        int max,min;
        if(m>=n){
            max=m-1;
            min=n-1;
        }else{
            max=n-1;
            min=m-1;
        }
        double result=1;
        for(int i=m+n-2;i>max;i--){
            result*=i;
            if(min>1){
                result/=min--;
            }
            //System.out.println(result);
        }
        return (int)(Math.round(result));
    }
}