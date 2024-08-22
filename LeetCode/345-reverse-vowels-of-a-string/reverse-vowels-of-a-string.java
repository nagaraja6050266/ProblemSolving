class Solution {
    public String reverseVowels(String s) {
        String vowels="aeiouAEIOU";
        char[] result=s.toCharArray();
        int left=0,right=result.length-1;
        while(left<right){
            if(vowels.indexOf(result[left])==-1){
                left++;
                continue;
            }
            if(vowels.indexOf(result[right])==-1){
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