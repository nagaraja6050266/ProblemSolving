import java.util.*;

// Mediator Interface
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

// Concrete Mediator (Chat Room)
class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
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
    private ChatMediator chatMediator;

    public User(String name, ChatMediator chatMediator) {
        this.name = name;
        this.chatMediator = chatMediator;
    }

    public void sendMessage(String message) {
        System.out.println(this.name + " sends: " + message);
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
        ChatMediator chatRoom = new ChatRoom();

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
    }
}
