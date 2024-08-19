import java.util.Random;
import  java.util.Arrays;

public class RandomPasswordGenerator {
    static int i = 0;
    public static void main(String[] args) {
        int length = 8;
        //2 Caps,2 small, 2 number, 2 symbol
        char[] password = new char[length];
        //Arrays.fill(password,'a');
        password = generateRandom(password, 33, 47, 2);
        password = generateRandom(password, 65, 90, 2);
        password = generateRandom(password, 97, 122, 2);
        password = generateRandom(password, 48, 57, 2);
        password = randomSort(password);
        String result=new String(password);
        System.out.println(result);
    }

    private static char[] generateRandom(char[] password, int start, int end, int arrayIndex) {
        Random rand = new Random();
        for (int j = 0; j<arrayIndex; j++) {
            password[i++] = (char) rand.nextInt(start, end);
        }
        return password;
    }

    private static char[] randomSort(char[] password){
        Random rand=new Random();
        for (int i=0;i<password.length;i++){
            int randomIndex=rand.nextInt(0, password.length-1);
            char temp=password[i];
            password[i]=password[randomIndex];
            password[randomIndex]=temp;
        }
        return password;
    }
}
