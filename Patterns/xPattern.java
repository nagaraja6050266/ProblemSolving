/*



*/

import java.util.Scanner;
class xPattern{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the string: ");
        String s=sc.nextLine();
        int n=s.length();
        for(int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (i==j || j==n-i-1){
                    System.out.print(s.charAt(j));
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }
}