
import java.util.ArrayList;

enum Gender {
    MALE, FEMALE
}

class Member {

    public String name;
    public Member father;
    public Member mother;
    public ArrayList<Member> siblings = new ArrayList<>();
    public Gender gender;

    public ArrayList<Member> children = new ArrayList<>();

    public void setFather(Member father) {
        this.father = father;
        father.children.add(this);
    }

    public void setMother(Member mother) {
        this.mother = mother;
        mother.children.add(this);
    }

    Member(String name,Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public void setChildren(Member child){
        this.children.add(child);
        if(this.gender==Gender.MALE){
            child.setFather(this);
        } else {
            child.setMother(this);
        }
    }

}

class Relations {

    public static void main(String[] args) {
        Member mani = new Member("Manikandan", Gender.MALE);
        Member esakki = new Member("Esakki Muthu",Gender.MALE);
        Member masanam = new Member("Masanam",Gender.MALE);
        Member dummy = new Member("Suppaiya",Gender.MALE);
        Member Manimother = new Member("Bala",Gender.MALE);


        mani.setFather(esakki);
        esakki.setFather(masanam);
        masanam.setFather(dummy);

        System.out.println(mani.name);


    }

}
