class RightToLeftVertical{
    public static void main(String args[]){
        int n=3,c=1;
        for (int i=1;i<=n;i++){
            int firstValue=0;
            for (int j=1;j<=n;j++){
                if(i+j>n){
                    if(firstValue==0){
                        firstValue=c;
                    }
                    System.out.print(c+" ");
                    c-=j;
                }
                else{
                    System.out.print("  ");
                }
            }
            c=firstValue+n-i+1;
            System.out.println();
        }
    }
}