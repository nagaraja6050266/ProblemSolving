package OtherProblems;

class longestWordInString{
    public static void main(String args[]){
        String s="I loveddd India";
        int maxLength=0,startIndex,lastIndex,maxStart=0,maxStop=0;
        for(int i=0;i<s.length();i++){
            int length=0;
            startIndex=i-1;
            while(i<s.length() && s.charAt(i++)!=' '){
                length++;
            }
            lastIndex=i;
            if(maxLength<length){
                maxStart=startIndex;
                maxStop=lastIndex;
                maxLength=length;
            }
        }
        //System.out.println(maxLength+" "+maxStart+" "+maxStop);
        System.out.print(s.substring(maxStart,maxStop));
    }
}