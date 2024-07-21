/*

PS C:\Users\Nagaraja\Documents\Problem Solving\Patterns> java numberSpiral
Enter the n value: 3
3 3 3 3 3 
3 2 2 2 3 
3 2 1 2 3 
3 2 2 2 3 
3 3 3 3 3 
PS C:\Users\Nagaraja\Documents\Problem Solving\Patterns> java numberSpiral
Enter the n value: 5
5 5 5 5 5 5 5 5 5 
5 4 4 4 4 4 4 4 5 
5 4 3 3 3 3 3 4 5 
5 4 3 2 2 2 3 4 5 
5 4 3 2 1 2 3 4 5 
5 4 3 2 2 2 3 4 5 
5 4 3 3 3 3 3 4 5
5 4 4 4 4 4 4 4 5
5 5 5 5 5 5 5 5 5

*/


import java.util.Scanner;
class numberSpiral{
    public static void main(String args[]){

        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the n value: ");
        int num=sc.nextInt();

        int i,j;
        int n=(2*num)-1,c=num;
        int start=0,mid=n;

        for(i=0;i<n;i++){
            //start
            for(j=0;j<start;j++){
                System.out.print((c--)+" ");
            }
            
            //Mid
            for(j=0;j<mid;j++){
                System.out.print(c+" ");
            }
            
            //end
            for (j=0;j<start;j++){
                System.out.print((++c)+" ");
            }
            System.out.println();

            if (i<n/2){
                start++;
                mid-=2;
            }
            else{
                start--;
                mid+=2;
            }
        }   
    }
}