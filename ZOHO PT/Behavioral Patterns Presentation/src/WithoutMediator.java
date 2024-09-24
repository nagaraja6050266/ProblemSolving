import java.util.ArrayList;
import java.util.List;

// User class that communicates directly with other users
class User2 {
    private String name;
    private List<User2> contacts;

    public User2(String name) {
        this.name = name;
        this.contacts = new ArrayList<>();
    }

    public void addContact(User2 user) {
        contacts.add(user);
    }

    public void sendMessage(String message) {
        System.out.println("\n" + this.name + " sends: " + message);
        for (User2 contact : contacts) {
            contact.receiveMessage(message, this);
        }
    }

    public void receiveMessage(String message, User2 sender) {
        System.out.println(this.name + " receives from " + sender.getName() + ": " + message);
    }

    public String getName() {
        return name;
    }
}

class WithoutMediator {
    public static void main(String[] args) {
        // Create users
        User2 nagaraja = new User2("Nagaraja");
        User2 arumugam = new User2("Arumugam");
        User2 chandhru = new User2("Chandhru");
        User2 madhav = new User2("Madhav");

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

        nagaraja.sendMessage("Hello everyone!");

        arumugam.sendMessage("Hi!");

        madhav.sendMessage("Good morning!");
    }
}
