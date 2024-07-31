class secondLargest{
    public static void main(String args[]){
        int[] arr={1,9,3,8,5,6,7};
        int f=arr[0],s=arr[1];
        for (int i:arr){
            if(i>f){
                s=f;
                f=i;
            }
            else if(i>s){
                s=i;
            }
        }
        System.out.print(s);
    }
}