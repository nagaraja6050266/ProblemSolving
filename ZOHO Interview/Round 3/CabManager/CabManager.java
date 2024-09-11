
import java.util.ArrayList;
import java.util.List;

class Cab {

    private String name;
    private int id;
    private String model;
    private String type;
    private int rent;

    public int calculateCabExpense() {
        return 0;
    }

    Cab(String name, int id, String model, String type) {
        this.name = name;
        this.id = id;
        this.model = model;
        this.type = type;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

}

class Employee {

    private String name;
    private int id;
    private String type;

    public Employee(String name, int id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }
}

class Trip {

    private Cab cab;
    private Employee employee;
    private Driver driver;
    private int date;
    private String pickUpPoint;
    private String dropPoint;

    public void tripReport() {
        System.out.println("Current Trip Report: \nEmployee: " + employee + "\nCab: " + cab + "\nDriver: " + driver + "Date: " + date);
    }

    public Trip(Cab cab, Employee employee, Driver driver, int date, String pickUpPoint, String dropPoint) {
        this.cab = cab;
        this.employee = employee;
        this.driver = driver;
        this.date = date;
        this.pickUpPoint = pickUpPoint;
        this.dropPoint = dropPoint;
    }

    //Getter
    public int getDate() {
        return date;
    }

    public Cab getCab() {
        return cab;
    }

}

class Driver {

    private String name;
    private int id;

    public Driver(String name, int id) {
        this.name = name;
        this.id = id;
    }

}

class CabManager {

    static List<Trip> trips = new ArrayList<>();
    static List<Cab> cabs = new ArrayList<>();
    static List<Employee> employees = new ArrayList<>();
    static List<Driver> drivers = new ArrayList<>();

    public static void makeTrip(Trip trip) {
        trips.add(trip);
    }

    public void listAllCabs() {
        for (Cab cab : cabs) {
            System.out.println();
        }
    }

    public static void generateReport() {
        for (Trip trip : trips) {
            System.out.print(trip.getDate());
        }
    }

    public static void main(String[] args) {
        Cab cab1 = new Cab("Cab1", 1, "Cab1Model", "First Class");
        Cab cab2 = new Cab("Cab2", 2, "Cab1Model", "First Class");
        Driver driver1 = new Driver("Driver1", 1);
        Driver driver2 = new Driver("Driver2", 2);
        Employee employee1 = new Employee("Employee1", 1, "Senior");
        Employee employee2 = new Employee("Employee2", 2, "Junior");
        cabs.add(cab1);
        cabs.add(cab2);
        employees.add(employee1);
        employees.add(employee2);

        Trip trip = new Trip(cab2, employee2, driver2, 3, "Zoho", "Home");
        makeTrip(trip);
        trips.get(0).tripReport();
    }
}
