class CrossNumberPattern{
    public static void main(String args[]){
        int n=5,c=1;
        for(int i=1;i<=n;i++){
            int firstValue=0,decrement=n-1,increment=i+1;
            for(int j=1;j<=n;j++){
                if(firstValue==0){
                    firstValue=c;
                }
                if(i+j<=n+1){
                    System.out.print(c+" ");
                    c+=increment;
                    increment++;   
                }
                else{
                    c-=increment-1;
                    c+=n;
                    while(j<=n){
                        System.out.print(c+" ");
                        c+=decrement--;
                        j++;
                    }
                }
            }
            System.out.println();
            c=firstValue+i;
        }
    }
}