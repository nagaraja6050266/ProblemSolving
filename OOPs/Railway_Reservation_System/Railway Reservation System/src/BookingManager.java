import java.util.*;

public class BookingManager {

    private static Map<String, Integer> seats = new HashMap<>();

    private static void setSeats(int upper, int middle, int lower) {
        seats.put("U", upper);
        seats.put("M", middle);
        seats.put("L", lower);
    }

    private static int availableRAC = 1;
    private static int availableWaitingList = 1;

    static ArrayList<Passenger> passengersList = new ArrayList<>();
    static Queue<Passenger> waitingList = new LinkedList<>();
    static Queue<Passenger> racList = new LinkedList<>();

    private static void addToWaitingList(Passenger passenger) {
        if (availableWaitingList == 0) {
            System.out.println("Waiting List Filled");
            return;
        }
        waitingList.offer(passenger);
        availableWaitingList--;
        passenger.setStatus("Waiting List");
    }

    private static void addToRAC(Passenger passenger) {
        if (availableRAC == 0) {
            addToWaitingList(passenger);
            return;
        }
        racList.offer(passenger);
        availableRAC--;
        passenger.setStatus("RAC");
    }

    private static boolean allotBerth(Passenger passenger) {
        String preferredBerth = passenger.getPreferredBerth();
        if (seats.get(preferredBerth) > 0) {                            //If preferred berth is available
            seats.put(preferredBerth, seats.get(preferredBerth) - 1);
            passenger.allotSeat(3, preferredBerth);
            passenger.setStatus("Allotted");
            return true;
        }
        for (Map.Entry<String, Integer> entries : seats.entrySet()) {
            if (entries.getKey() != preferredBerth) {
                if (entries.getValue() > 0) {
                    seats.put(entries.getKey(), entries.getValue() - 1);
                    passenger.setStatus("Allotted");
                    passenger.allotSeat(1, entries.getKey());
                    return true;
                }
            }
        }
        return false;
    }


    private static void cancelAllottedSeat(Passenger passenger) {
        passengersList.remove(passenger);
        String allottedBerth = passenger.getAllotedBerth();
        seats.put(allottedBerth, seats.get(allottedBerth) + 1);
        System.out.println(passenger.getName() + " removed");
        moveRacToBerth();
    }

    private static void moveRacToBerth() {
        Passenger racPassenger = racList.poll();
        if (racPassenger != null) {
            bookTicket(racPassenger);
            availableRAC++;
            moveWaitingToRac();
        }
    }

    private static void moveWaitingToRac() {
        Passenger waitingPassenger = waitingList.poll();
        if (waitingPassenger != null) {
            bookTicket(waitingPassenger);
            availableWaitingList++;
        }
    }

    private static void bookTicket(Passenger passenger) {
        if (!allotBerth(passenger)) {
            addToRAC(passenger);
        }
        System.out.println("\n\nBooking Invoice");
        passenger.getDetails();
    }

    private static void cancelTicket(Passenger passenger) {
        String status = passenger.getStatus();
        switch (status) {
            case "Allotted":
                cancelAllottedSeat(passenger);
                break;
            case "RAC":
                racList.remove(passenger);
                moveWaitingToRac();
                break;
            case "Waiting List":
                waitingList.remove(passenger);
                availableWaitingList++;
                break;
            default:
                System.out.println("Haven't booked any seats");
        }
    }


    public static void main(String[] args) {
        setSeats(1, 1, 1);
        Passenger mani = new Passenger("Mani", 2, "L");
        Passenger siva = new Passenger("Siva", 3, "L");
        Passenger inr = new Passenger("INR", 4, "L");
        Passenger karthi = new Passenger("karthi", 5, "L");
        Passenger bala = new Passenger("Bala", 9, "L");
        Passenger alagu = new Passenger("alagu", 8, "L");
        bookTicket(mani);
        bookTicket(siva);
        bookTicket(inr);
        bookTicket(karthi);
        bookTicket(bala);
        bookTicket(alagu);

        cancelTicket(mani);
        cancelTicket(inr);
        cancelTicket(siva);

    }
}