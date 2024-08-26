import java.util.Stack;
public class RemoveAdjacentDuplicates {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<Character>();
        String s = "abbax";
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (!stack.isEmpty() && s.charAt(i) == stack.peek()) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
            System.out.println(stack);
        }
        System.out.println(stack.toString());
    }
}
