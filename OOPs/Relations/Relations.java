
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

    public ArrayList<Member> childrens = new ArrayList<>();

    public void setFather(Member father) {
        this.father = father;
        father.childrens.add(this);
    }

    public void setMother(Member mother) {
        this.mother = mother;
        mother.childrens.add(this);
    }

    public void setSibling(Member sibling) {
        this.siblings.add(sibling);
        sibling.siblings.add(this);
    }

    Member(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public void setChildren(Member child) {
        this.childrens.add(child);
        if (this.gender == Gender.MALE) {
            child.setFather(this);
        } else {
            child.setMother(this);
        }
    }

    public void getAtthaPonnu() {
        ArrayList<Member> aunties = new ArrayList<>();
        for (Member member : this.father.siblings) {
            if (member.gender == Gender.FEMALE) {
                aunties.add(member);
            }
        }
        for (Member aunty : aunties) {
            for (Member child : aunty.childrens) {
                if (child.gender == Gender.FEMALE) {
                    System.out.println(child.name);
                }
            }
        }
    }

}

class Relations {

    public static void main(String[] args) {
        Member mani = new Member("Manikandan", Gender.MALE);
        Member esakki = new Member("Esakki Muthu", Gender.MALE);
        Member masanam = new Member("Masanam", Gender.MALE);
        Member dummy = new Member("Suppaiya", Gender.MALE);
        Member Manimother = new Member("Bala", Gender.MALE);
        Member sandhiya = new Member("Sandhiya", Gender.FEMALE);
        Member muthulakshmi = new Member("Muthulakshmi", Gender.FEMALE);

        mani.setFather(esakki);
        esakki.setFather(masanam);
        masanam.setFather(dummy);
        sandhiya.setChildren(muthulakshmi);
        esakki.setSibling(sandhiya);

        mani.getAtthaPonnu();

    }

}
