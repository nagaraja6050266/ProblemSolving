package OtherProblems;

class rangeReverse{
    public static void main(String args[]){
        int[] array={1,2,3,4,5,6,7,8,9,10,11};
        int k=10,i;
        int n=array.length;
        for(i=0;i<array.length-k;i+=k){
            reverse(array,i,i+k-1);
        }
        if(i+k>n){
            reverse(array,i,n-1);
        }
        printArray(array);
    }
    static void reverse(int[] array,int start,int stop){
        while(start<stop){
            swap(array,start++,stop--);
        }
    }
    static void swap(int[] array,int start,int stop){
        int t=array[start];
        array[start]=array[stop];
        array[stop]=t;
    }
    static void printArray(int[] array){
        for(int i:array){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}