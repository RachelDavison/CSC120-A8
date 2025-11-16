/* This is a stub for the Cafe class */
public class Cafe extends Building implements CafeRequirements{

    private int nCoffeeOunces; 
    private int nSugarPackets; 
    private int nCreams; 
    private int nCups;
    private final int maxCoffeeOunces = 1000;
    private final int maxSugarPackets = 500;
    private final int maxCreams = 500;
    private final int maxCups = 200;

    /**
     * Constructor for Cafe class
     * @param name
     * @param address
     * @param nFloors
     */
    public Cafe(String name, String address, int nFloors) {
        super(name, address, nFloors);
    }

    /**
     * Constructor for Cafe class with inventory parameters
     * @param name
     * @param address
     * @param nFloors
     * @param nCoffeeOunces
     * @param nSugarPackets
     * @param nCreams
     * @param nCups
     */
    public Cafe(String name, String address, int nFloors, int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        super(name, address, nFloors);
        this.nCoffeeOunces = nCoffeeOunces;
        this.nSugarPackets = nSugarPackets;
        this.nCreams = nCreams;
        this.nCups = nCups;
    }

    /**
     * Overloaded constructor for Cafe class with default 1 floor
     * @param name
     * @param address
     */
    public Cafe(String name, String address) {
        this(name, address, 1);
    }

    /**
     * Overriden method, displays the options available in the Cafe class
     */
    public void showOptions(){
        super.showOptions();
        System.out.println(" + sellCoffee(size, nSugarPackets, nCreams)");
    }

    /**
     * Overrides goToFloor to account for lack of elevator
     * @param floorNum
     */
    public void goToFloor(int floorNum) {
        if (floorNum > 1) {
            throw new RuntimeException("Only the first floor is accessible to customers.");
        }
        super.goToFloor(floorNum);
    }

    /**
     * Sells a coffee with specified size, sugar packets, and creams
     * @param size
     * @param nSugarPackets
     * @param nCreams
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams) {
        if (this.nCoffeeOunces < size || this.nSugarPackets < nSugarPackets || this.nCreams < nCreams || this.nCups < 1) {
            restock(maxCoffeeOunces, maxSugarPackets, maxCreams, maxCups);
        }
        this.nCoffeeOunces -= size;
        this.nSugarPackets -= nSugarPackets;
        this.nCreams -= nCreams;
        this.nCups -= 1;
    }

    /**
     * Overloaded sellCoffee method with no sugar or cream
     * @param size
     */
    public void sellCoffee(int size) {
        sellCoffee(size, 0, 0);
    }

    /**
     * Restocks the cafe with specified amounts of coffee, sugar packets, creams, and cups
     * @param nCoffeeOunces
     * @param nSugarPackets
     * @param nCreams
     * @param nCups
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
    }
    
    public static void main(String[] args) {
        new Cafe("Campus Cafe", "100 College St, Northampton, MA 01063", 1);
    }
    
}
