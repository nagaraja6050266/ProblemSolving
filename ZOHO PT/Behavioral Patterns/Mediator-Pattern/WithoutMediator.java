import java.util.ArrayList;
import java.util.List;

// User class that communicates directly with other users
class User {
    private String name;
    private List<User> contacts;

    public User(String name) {
        this.name = name;
        this.contacts = new ArrayList<>();
    }

    public void addContact(User user) {
        contacts.add(user);
    }

    public void sendMessage(String message) {
        System.out.println("\n"+this.name + " sends: " + message);
        for (User contact : contacts) {
            contact.receiveMessage(message, this);
        }
    }

    public void receiveMessage(String message, User sender) {
        System.out.println(this.name + " receives from " + sender.getName() + ": " + message);
    }

    public String getName() {
        return name;
    }
}

class WithoutMediator{
    public static void main(String[] args) {
        // Create users
        User nagaraja = new User("Nagaraja");
        User arumugam = new User("Arumugam");
        User chandhru = new User("Chandhru");
        User madhav = new User("Diana");

        // Set up direct connections (contacts)
        nagaraja.addContact(arumugam);
        nagaraja.addContact(chandhru);
        nagaraja.addContact(madhav);

        arumugam.addContact(nagaraja);
        arumugam.addContact(chandhru);
        arumugam.addContact(madhav);

        chandhru.addContact(nagaraja);
        chandhru.addContact(arumugam);
        chandhru.addContact(madhav);


        madhav.addContact(nagaraja);
        madhav.addContact(arumugam);
        madhav.addContact(chandhru);

        // Alice sends a message to all her contacts
        nagaraja.sendMessage("\nHello everyone!");

        // Bob sends a message
        arumugam.sendMessage("Hi Alice!");

        // Diana sends a message
        madhav.sendMessage("Good morning!");
    }
}
