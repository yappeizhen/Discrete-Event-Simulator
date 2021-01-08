package cs2030.simulator;

/**
 * An immutable class which has a unique Customer ID and the time of arrival.
 */
public class TypicalCustomer extends Customer{
    public TypicalCustomer(int custID, double arrTime) {
        super(custID, arrTime);
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.getID());
    }
}
