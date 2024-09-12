import java.util.Scanner;

class Main{
    public static void main(String args[]){
        int choice;
        Scanner sc=new Scanner(System.in);

        Users usersObj=new Users();
        while(true){
            System.out.println("Railway Ticket Reservation System Welcomes you...!\nChoose the one from the menu\n1. New User\n2. Existing User\n3. Exit");
            choice=sc.nextInt();
            switch(choice){
                case 1:
                    usersObj.createUser();
                    break;
                case 2:
                    while(!(usersObj.login())){
                        continue;
                    }
                    System.out.println("Successfully Logged IN");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }  
    }
}