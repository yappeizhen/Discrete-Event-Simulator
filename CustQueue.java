package cs2030.simulator;

import java.util.ArrayList;

public class CustQueue extends ArrayList<Customer> {
    private final int maxSize;

    public CustQueue(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        return this.maxSize;
    }
    
    public boolean isFull() {
        return this.size() >= maxSize;
    }

    public boolean isNext(Customer cust) {
        if (this.get(0).getID() == cust.getID()) {
            return true;
        }
        return false;
    }

    public Customer takeNext() {
        return this.remove(0);
    }

    public boolean add(Customer cust) {
        if (this.size() < maxSize) {
           return super.add(cust);
        } 
        return false;
    }
}
