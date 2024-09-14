public class Passenger {
    private String name;
    private int id;
    private String status = "Not Allotted";
    private int seatNumber;
    private String allotedBerth;
    private String preferredBerth = "RAC";

    public void allotSeat(int seatNumber, String berth) {
        this.allotedBerth = berth;
        this.seatNumber = seatNumber;
    }

    //Setters
    public void setStatus(String status) {
        this.status = status;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getAllotedBerth() {
        return allotedBerth;
    }

    public String getPreferredBerth() {
        return preferredBerth;
    }

    public String getStatus() {
        return status;
    }

    //Constructor
    Passenger(String name, int id, String preferredBerth) {
        this.id = id;
        this.name = name;
        this.preferredBerth = preferredBerth;
    }

    //Display Details
    public void getDetails() {
        System.out.print("\nName: " + name + "\nID: " + id + "\nStatus: ");
        if (status.equals("Allotted")) {
            System.out.println("Allotted\nSeat Number: " + seatNumber + "\nBerth: " + allotedBerth);
        } else {
            System.out.println(status);
        }
    }
}
