public class Passenger {
    private String name;
    private int id;
    private boolean isAllotted = false;
    private int seatNumber;
    private String allotedBerth;
    private String preferredBerth = "RAC";

    //Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getAllotedBerth() {
        return allotedBerth;
    }

    public String getPreferredBerth() {
        return preferredBerth;
    }

    //Constructor

    Passenger(String name, int id, String preferredBerth) {
        this.id = id;
        this.name = name;
        this.preferredBerth = preferredBerth;
    }

    //Display Details
    public void getDetails() {
        System.out.println("Name: " + name + "\nID: " + id);
        if (isAllotted) {
            System.out.println("Seat Number: " + seatNumber + "\nBerth: " + allotedBerth);
        } else {
            System.out.println("No Seat Allotted");
        }
    }

}
