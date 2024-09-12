
import java.util.ArrayList;

class Member {

    public String name;
    public Member father;
    public Member mother;
    public ArrayList<Member> siblings = new ArrayList<>();
    public String gender;

    public void setFather(Member father) {
        this.father = father;
        // System.out.println("Setted" + father.name);
    }

    public void setMother(Member mother){
        this.mother=mother;
    }

    Member(String name) {
        this.name = name;
    }

}

class Relations {

    public static void main(String[] args) {
        Member mani = new Member("Manikandan");
        Member esakki = new Member("Esakki Muthu");
        Member masanam = new Member("Masanam");
        Member dummy = new Member("Suppaiya");
        Member Manimother = new Member("Bala");


        mani.setFather(esakki);
        esakki.setFather(masanam);
        masanam.setFather(dummy);

        System.out.println(mani.name);


    }

}
