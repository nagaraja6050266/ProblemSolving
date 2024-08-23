class Solution {
    public double myPow(double x, int n) {
        double result = 1,m=(double)n;
        boolean negative=false;
        if(x==1){
            return 1;
        }
        if(x==-1){
            return (n%2==0)?1:-1;
        }
        if (n < 0) {
            m = -m;
            x=1/x;
        }
        //System.out.println(n);
        for (int i = 0; i < m; i++) {
            if (result==0){
                return 0;
            }
            result *= x;
            //System.out.println(result);
        }
        return result;
    }
}