package OtherProblems;

class linearPattern{
    public static void main(String[] args) {
        int n=16,k=7,c=n+k;
        while(c>0){
            c-=k;
            System.out.print(c+" ");
        }
        while(c<n){
            c+=k;
            System.out.print(c+" ");
        }
    }
}