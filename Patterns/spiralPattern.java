public class spiralPattern{
    public static void main(String[] args){
        char star='*';
        char dot='.';
        char printable=star;
        int n=8;
        int midCount=n;

        int[][] arr= new int[8][8];
        for (int i=0;i<n/2;i++){
            for (int j=i;j<n/2;j++){
                arr[i][j]=printable;
            }
            printable=(printable==star)?dot:star;
        }
        spiralPattern sp=new spiralPattern();
        sp.printArray(arr);
    }

    public void printArray(int[][] arr){
        int n=arr.length;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                System.out.print(arr[i][j]+' ');
            }
            System.out.println();
        }
    }
}
