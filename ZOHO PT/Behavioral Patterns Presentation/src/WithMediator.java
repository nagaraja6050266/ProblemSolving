import java.util.*;

// Concrete Mediator (Chat Room)
class ChatRoom {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void sendMessage(String message, User user) {
        for (User u : users) {
            if (u != user) {  // Message should not be sent back to the sender
                u.receiveMessage(message);
            }
        }
    }
}

// User (Colleague)
class User {
    private String name;
    private ChatRoom chatMediator;

    public User(String name, ChatRoom chatMediator) {
        this.name = name;
        this.chatMediator = chatMediator;
    }

    public void sendMessage(String message) {
        System.out.println("\n"+this.name + " sends: " + message);
        chatMediator.sendMessage(message, this);
    }

    public void receiveMessage(String message) {
        System.out.println(this.name + " receives: " + message);
    }
}

// Usage
public class WithMediator {
    public static void main(String[] args) {
        // Create the mediator (Chat Room)
        ChatRoom chatRoom = new ChatRoom();

        // Create users and register them with the chat room
        User nagaraja = new User("Nagaraja", chatRoom);
        User arumugam = new User("Arumugam", chatRoom);
        User chandhru = new User("Chandhru", chatRoom);

        chatRoom.addUser(nagaraja);
        chatRoom.addUser(arumugam);
        chatRoom.addUser(chandhru);

        // Users send messages through the mediator (Chat Room)
        nagaraja.sendMessage("Vanakkam Nanbargale!");
        arumugam.sendMessage("Vanakkam da maapla...!");
        chandhru.sendMessage("Hello Guys");

    }
}
