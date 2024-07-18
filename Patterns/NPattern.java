import java.util.*;
class NPattern{
    public static void main(String args[]){
        
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the n value: ");
        int n=sc.nextInt();

        sc.nextLine();

        System.out.println("Enter the string: ");
        String string=sc.nextLine();

        int i=0,j=0;
        int c=0;
        int len=string.length();

        char[][] matrix=new char[n][len];

        //Fill Spaces
        for(int a=0;a<n;a++){
            for (int b=0;b<len;b++){
                matrix[a][b]=' ';
            }
        }

        try{
            while(c<len){
                //Move down
                while(i<n){
                    matrix[i++][j]=string.charAt(c++);
                }
                i-=2;
                j++;

                //Move UpCross
                while(i>0){
                    matrix[i--][j++]=string.charAt(c++);
                }
            }
        }
        catch (Exception e){
        }
        
        NPattern np=new NPattern();
        np.printMatrix(matrix);       
    }
    public void printMatrix(char[][] arr){
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}