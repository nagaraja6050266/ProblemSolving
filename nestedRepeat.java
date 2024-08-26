import java.util.Stack;

class nestedRepeat {

    public static void main(String[] args) {
        String s = "3[a2[bc]]";
        char[] charArray = s.toCharArray();
        Stack<String> stack = new Stack<>();
        for (char i : charArray) {
            if (i != ']') {
                stack.push(Character.toString(i));
            } else {
                String temp = "";
                while (!stack.peek().equals("[")) {
                    temp = stack.pop() + temp;
                }
                stack.pop();
                stack.push(temp.repeat(Integer.parseInt(stack.pop())));
            }
        }
        printStack(stack);
    }

    static void printStack(Stack<String> stack) {
        String s = "";
        for (String i : stack) {
            s += i;
        }
        System.out.println(s);
    }
}
