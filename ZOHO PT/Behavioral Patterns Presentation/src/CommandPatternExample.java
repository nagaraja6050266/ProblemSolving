import java.util.*;

interface TextFileCommand {
    String execute();
}

class TextFile {
    private String name;
    public String open() {
        return "Opening file: " + name;
    }
    public String save() {
        return "Saving file: " + name;
    }

    public TextFile(String name) {
        this.name = name;
    }

}

class OpenTextFileCommand implements TextFileCommand {

    private TextFile textFile;

    public OpenTextFileCommand(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public String execute() {
        return textFile.open();
    }
}

class SaveTextFileCommand implements TextFileCommand {
    private TextFile textFile;

    public SaveTextFileCommand(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public String execute() {
        return textFile.save();
    }
}

class TextFileCommandExecutor {
    private final List<TextFileCommand> textFileCommands
            = new ArrayList<>();

    public String executeCommand(TextFileCommand textFileCommand) {
        textFileCommands.add(textFileCommand);
        return textFileCommand.execute();
    }
}

class CommandPatternExample {
    public static void main(String[] args) {

        TextFileCommandExecutor textFileCommandExecutor = new TextFileCommandExecutor();
        TextFile file = new TextFile("balancesheet.txt");
        TextFileCommand openCommand = new OpenTextFileCommand(file);
        TextFileCommand saveCommand=new SaveTextFileCommand(file);

//        System.out.println(file.open());
        System.out.println(textFileCommandExecutor.executeCommand(openCommand));

//        System.out.println(file.save());
        System.out.println(textFileCommandExecutor.executeCommand(saveCommand));

    }
}