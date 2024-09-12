import java.util.Scanner;
class Users{
    Scanner sc=new Scanner(System.in);
    public static int count;
    User theUsers[] = new User[10];

    public void createUser(){
        boolean flag=false;
        String username,password,reEnterPassword;
        while(true){
            System.out.print("Create a Username: ");
            username=sc.nextLine();
            for (int i=0;i<count;i++){
                if(theUsers[i].username.equals(username)){
                    System.out.println("Username already Exists!");
                    flag=true;
                    break;
                }
            }
            if(flag){
                flag=false;
                continue;
            }
            System.out.print("Enter password: ");
            password=sc.nextLine();
            System.out.print("Re Enter password: ");
            reEnterPassword=sc.nextLine();

            if(!(password.equals(reEnterPassword))){
                System.out.println("Password doesnot match");
                flag=true;
                continue;
            }
            else{
                User u=new User(username,password);
                theUsers[count++]=u;
                System.out.println("Created a User with username:"+username);
            }
            break;
        }
    }
    
    public boolean login(){
        int i;
        System.out.print("Enter Username: ");
        String username=sc.nextLine();
        for (i=0;i<count;i++){
            if(theUsers[i].username.equals(username)){
                break;
            }
        }
        if(i==count){
            System.out.print("Username not found");
            return false;
        }
        System.out.print("Enter password: ");
        String password=sc.nextLine();
        if(theUsers[i].password.equals(password)){
            System.out.println("Welcome "+username);
            return true;
        }
        else{
            System.out.println("Wrong Password");
            return false;
        }
    }
}
