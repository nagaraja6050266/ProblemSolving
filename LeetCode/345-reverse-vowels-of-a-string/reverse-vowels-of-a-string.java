class Solution {
    public String reverseVowels(String s) {
        char[] result=s.toCharArray();
        int left=0,right=result.length-1;
        while(left<right){
            if(!(result[left]=='a'||result[left]=='e'||result[left]=='i'||result[left]=='o'||result[left]=='u'||result[left]=='A'||result[left]=='E'||result[left]=='I'||result[left]=='O'||result[left]=='U')){
                left++;
                continue;
            }
            if(!(result[right]=='a'||result[right]=='e'||result[right]=='i'||result[right]=='o'||result[right]=='u'||result[right]=='A'||result[right]=='E'||result[right]=='I'||result[right]=='O'||result[right]=='U')){
                right--;
                continue;
            }
            swap(result,left++,right--);
        }
        return new String(result);
    }
    static void swap(char[] array,int left,int right){
        char t=array[left];
        array[left]=array[right];
        array[right]=t;
    }
}