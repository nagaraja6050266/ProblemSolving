
class borderedTraingle{
    public static void main(String[] args) {
        int n=5,left=n,right=left;
        for(int i=1;i<n;i++){
            for(int j=1;j<=2*n-1;j++){
                if(j==left || j==right)
                    System.out.print('*');
                else
                    System.out.print(' ');
            }
            left--;
            right++;
            System.out.println();
        }
        System.out.println("*".repeat(2*n-1));
    }
}