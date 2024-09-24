import java.util.Stack;

class Memento {

    private final String state;  // The state to be saved

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class TextEditor {       //Originator
    private String content;  // Internal state

    public void write(String content) {
        this.content = content;
    }

    public Memento save() {
        return new Memento(content);  // Save the current state as Momento
    }

    public void restore(Memento memento) {
        this.content = memento.getState();  // Restore the saved state
    }

    public String getContent() {
        return content;
    }
}

class Caretaker {
    private Stack<Memento> history = new Stack<>();  // Stores memento history

    public void save(TextEditor editor) {
        history.push(editor.save());  // Save current state
    }

    public void undo(TextEditor editor) {
        if (!history.isEmpty()) {
            editor.restore(history.pop());  // Restore the previous state
        } else {
            System.out.println("No more undos.");
        }
    }
}

class MementoPatternExample {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Caretaker caretaker = new Caretaker();

        editor.write("First sentence.");
        caretaker.save(editor);  // Save the state
        System.out.println("Content: " + editor.getContent());

        editor.write("Second sentence.");
        caretaker.save(editor);  // Save the state again
        System.out.println("Content: " + editor.getContent());

        editor.write("Third sentence.");
        System.out.println("Content: " + editor.getContent());

        // Undo to previous state
        caretaker.undo(editor);
        System.out.println("After undo: " + editor.getContent());

        // Undo to first state
        caretaker.undo(editor);
        System.out.println("After undo: " + editor.getContent());
    }
}




//Without Momento

//class TextEditor {
//    private String content;
//
//    public void write(String content) {
//        this.content = content;
//    }
//
//    // Exposing internal state via a getter
//    public String getState() {
//        return content;
//    }
//
//    // Manually restoring state
//    public void setState(String content) {
//        this.content = content;
//    }
//}
//
//
//class Caretaker {
//    private Stack<String> history = new Stack<>();  // Saving state directly
//
//    public void save(TextEditor editor) {
//        history.push(editor.getState());  // Directly accessing editor's state
//    }
//
//    public void undo(TextEditor editor) {
//        if (!history.isEmpty()) {
//            editor.setState(history.pop());  // Directly modifying state
//        }
//    }
//}
//
//class MementoPatternExample {
//    public static void main(String[] args) {
//        TextEditor editor = new TextEditor();
//        Caretaker caretaker = new Caretaker();
//
//        editor.write("First sentence.");
//        caretaker.save(editor);  // Saving state by directly accessing it
//        System.out.println("Content: " + editor.getState());
//
//        editor.write("Second sentence.");
//        caretaker.save(editor);
//        System.out.println("Content: " + editor.getState());
//
//        editor.write("Third sentence.");
//        System.out.println("Content: " + editor.getState());
//
//        // Undo to previous state
//        caretaker.undo(editor);
//        System.out.println("After undo: " + editor.getState());
//
//        // Undo again
//        caretaker.undo(editor);
//        System.out.println("After undo: " + editor.getState());
//    }
//}
//
