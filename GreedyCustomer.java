package cs2030.simulator;

public class GreedyCustomer extends Customer {
    
    public GreedyCustomer(int custID, double arrTime) {
        super(custID, arrTime); 
    }
    
    @Override 
    public boolean isGreedy() {
        return true;
    }

    @Override 
    public String toString() {
        return this.getID() + "(greedy)";
    }
}
