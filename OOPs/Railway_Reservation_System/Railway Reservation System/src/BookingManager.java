import java.util.*;

public class BookingManager {

    private static Map<String,Integer> seats=new HashMap<>();

    public static void setSeats(int upper, int middle, int lower){
        seats.put("U",upper);
        seats.put("M",middle);
        seats.put("L",lower);
    }

    private static int availaibleRAC = 5;
    private static int availableWaitingList = 10;

    ArrayList<Passenger> passengersList = new ArrayList<>();
    Queue<Passenger> waitingList = new LinkedList<>();
    Queue<Passenger> racList = new LinkedList<>();

    public void bookTicket(Passenger passenger){
        if(availableWaitingList==0){
            System.out.println("No seats available");
        } else {
            if (availaibleRAC==0){
                waitingList.offer(passenger);
            } else {
                String preferredBerth = passenger.getPreferredBerth();
                //Berth availablity
//                if(seats.get(preferredBerth)>0){
//
//                }

            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}