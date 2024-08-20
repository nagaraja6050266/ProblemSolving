
import java.util.Stack;

class nestedRepeat {

    public static void main(String[] args) {
        String s = "2[a2[bc]]";
        char[] charArray = s.toCharArray();
        Stack<String> stack = new Stack<String>();
        String result = "";
        // stack.push("a");
        for (char i : charArray) {
            if (i != ']') {
                stack.push(Character.toString(i));
            } else {
                while (!stack.peek().equals("[")) {
                    System.out.println("Current Character " + stack.peek());
                    result = stack.pop() + result;
                }
                stack.pop();
                result = result.repeat(Integer.parseInt(stack.pop()));
                System.out.println("Current result: " + result);
                stack.push(result);
            }

            System.out.println(result);
        }
    }
}
