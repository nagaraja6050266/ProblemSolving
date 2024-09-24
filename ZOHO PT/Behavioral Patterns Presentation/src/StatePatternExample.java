
interface State {
    void insertCoin();
    void dispenseItem();
}

//Context
class VendingMachine {
    private State noCoinState;
    private State hasCoinState;
    private State soldOutState;

    private State currentState;

    public VendingMachine() {
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldOutState = new SoldOutState(this);

        currentState = noCoinState; // Initial state is NoCoinState
    }

    public void setState(State state) {
        currentState = state;
    }

    public void insertCoin() {
        currentState.insertCoin();
    }

    public void dispenseItem() {
        currentState.dispenseItem();
    }

    public State getNoCoinState() {
        return noCoinState;
    }

    public State getHasCoinState() {
        return hasCoinState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }
}


//States
class NoCoinState implements State {
    private VendingMachine vendingMachine;

    public NoCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted.");
        vendingMachine.setState(vendingMachine.getHasCoinState());
    }

    @Override
    public void dispenseItem() {
        System.out.println("Please insert a coin first.");
    }
}

class HasCoinState implements State {
    private VendingMachine vendingMachine;

    public HasCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted.");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Item dispensed.");
        vendingMachine.setState(vendingMachine.getNoCoinState());
    }
}

class SoldOutState implements State {
    private VendingMachine vendingMachine;

    public SoldOutState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Machine is sold out.");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Machine is sold out.");
    }
}


public class StatePatternExample {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        machine.insertCoin(); // Coin inserted
        machine.dispenseItem(); // Item dispensed

        machine.dispenseItem(); // Please insert a coin first

        machine.insertCoin(); // Coin inserted
        machine.insertCoin(); // Coin already inserted
        machine.dispenseItem(); // Item dispensed
    }
}
