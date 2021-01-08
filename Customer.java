package cs2030.simulator;
import java.util.Comparator;

/**
 * An immutable class which has a unique Customer ID and the time of arrival.
 */
public abstract class Customer{
    private final int custID;
    private final double arrTime;
    
    /**
     * Constructor for the Customer class.
     * @param custID sets Customer's unique ID to custID
     * @param arrTime sets Customer's arrival time to arrTime
     */
    public Customer(int custID, double arrTime) {
        this.custID = custID;
        this.arrTime = arrTime;
    }

    /** 
     * Returns the ID of a Customer, and does not have any parameters.
     *  @return Customer's ID
     */
    public int getID() {
        return this.custID;
    }

    /** Returns the Customer's arrival time and has no parameters.
     *  @return Customer's arrival time
     */
    public double getArrTime() {
        return this.arrTime;
    }
        
    public boolean isGreedy() {
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(this.custID);
    }
}
