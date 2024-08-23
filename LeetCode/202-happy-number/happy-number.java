class Solution {
    public boolean isHappy(int n) {
        while(n/10>=1){
            int sum=0;
            while(n>0){
                sum+=((n%10)*(n%10));
                n/=10;
                //System.out.println(sum);
            }
            n=sum;
        }
        return n==1 || n==7;
    }
}