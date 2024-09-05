package OtherProblems;

class cielAndFloor{
    public static void main(String args[]){
        int[] arr={1,2,3,4,5};
        int x=5;
        int ciel=-1,floor,i,n=arr.length;

/*
        //Linear Search
        for (i=0;i<n;i++){
            if (arr[i]>=x){
                ciel=arr[i];
                break;
            }
        }
        if (i==n){
            ciel=-1;
            floor=arr[i-1];
        }
        else if(i==0){
            floor=-1;
        }
        else{
            floor=arr[i-1];
        }
        System.out.print("Floor: "+floor+"\nCiel: "+ciel);

*/

        //Binary Search
        int mid=n/2,l=0,r=n-1;
        while (l<r){
            System.out.println(l+" "+r+" "+mid+" ");
            mid=(r-l)/2+l;
            if (arr[mid]>x){
                r=mid-1;
            }
            else if(arr[mid]<x){
                l=mid+1;
            }
            else{
                break;
            }
        }
        System.out.println("Mid: "+mid);
    }
} 