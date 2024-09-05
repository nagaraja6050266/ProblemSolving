package OtherProblems;

import java.util.HashMap;

class isomorphicString{
    public static void main(String args[]){
        String a="aaabbbbbb",b="cccdddddd";
        int value;
        HashMap<Character,Integer> mapA=new HashMap<Character,Integer>();
        HashMap<Character,Integer> mapB=new HashMap<Character,Integer>();
        for (int i=0;i<a.length();i++){
            try{
                value=mapA.get(a.charAt(i));
                mapA.put(a.charAt(i),++value);
            }
            catch(Exception e){
                mapA.put(a.charAt(i),1);
            }
            try{
                value=mapB.get(b.charAt(i));
                mapB.put(b.charAt(i),++value);
            }
            catch(Exception e){
                mapB.put(b.charAt(i),1);
            }
        }
        if (mapA.values().containsAll(mapB.values()) && mapB.values().containsAll(mapA.values())){
            System.out.print("Isomorphic"+mapA.values()+mapB.values());
        }
        else{
            System.out.print("Not"+mapA.values()+mapB.values());
        }
    }
}