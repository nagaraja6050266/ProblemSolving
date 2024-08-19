class SnakePattern{
    public static void main(String args[]){
        int row=3,column=5,c=1,increment=1;
        for(int i=1;i<=row;i++){
            for(int j=1;j<=column;j++){
                System.out.print(c+" ");
                c+=increment;
            }
            System.out.println();
            if((i+1)%2==0){
                increment=-1;
            }
            else{
                increment=1;
            }
            c+=column+increment;
        }       
    }
}