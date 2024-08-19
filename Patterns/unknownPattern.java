
class unknownPattern{
    public static void main(String args[]){
        int n=5,c=1;
        for(int i=0;i<n;i++){
            int diff=i+1;
            int firstValue=0;
            for(int j=0;j<n-i;j++){
                if(firstValue==0){
                    firstValue=c;
                }
                System.out.print(c+" ");
                c+=diff++;
            }
            c=firstValue+i+2;
            System.out.println();
        }
    }
}