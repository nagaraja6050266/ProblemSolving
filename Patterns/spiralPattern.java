



import java.util.Scanner;
public class spiralPattern{
    public static void main(String[] args){
        char star='*';
        char dot='.';
        char printable=star;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the n value: ");
        int n=sc.nextInt();
        int midCount=n;
        char[][] arr= new char[n][n];
        for (int i=0;i<n/2;i++){
            for (int j=i;j<n/2;j++){
                arr[i][j]=printable;
            }
            printable=(printable==star)?dot:star;
        }
        spiralPattern sp=new spiralPattern();
        sp.printArray(arr);
    }

    public void printArray(char[][] arr){
        int n=arr.length;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
