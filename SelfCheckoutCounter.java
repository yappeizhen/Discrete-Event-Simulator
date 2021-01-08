package cs2030.simulator;

class SelfCheckoutCounter extends Server {
    private static CustQueue sharedQueue;
    private static SelfCheckoutCounter queueLocation;

    public SelfCheckoutCounter(int servID, int maxQueueLength) {
        super(servID, 0);
        SelfCheckoutCounter.sharedQueue = new CustQueue(maxQueueLength);
        SelfCheckoutCounter.queueLocation = this;
    }

    public SelfCheckoutCounter(int servID) {
        super(servID, 0);
    }

    public SelfCheckoutCounter(int servID, Customer customer, double timeAvail) {
        super(servID, customer, timeAvail, null);
    }

    public SelfCheckoutCounter getQueueLocation() {
        return this.queueLocation;
    } 

    @Override
    public boolean isSelfCheck() {
        return true;
    }

    @Override
    public CustQueue getQueue() {
        return this.sharedQueue;
    }

    @Override 
    public SelfCheckoutCounter serve(Customer cust, double newTime) {
        this.sharedQueue.remove(cust);
        return new SelfCheckoutCounter(this.getServID(), cust, newTime);
    }

    @Override
    public SelfCheckoutCounter queue(Customer customer) {
        sharedQueue.add(customer);
        return this;
    }

    @Override 
    public SelfCheckoutCounter finish() {
        return new SelfCheckoutCounter(this.getServID(), null, this.getTimeAvail());
    }

    @Override 
    public SelfCheckoutCounter rest(double restTime) {
        throw new IllegalStateException("Self-Check Counter does not rest");
    }

    @Override
    public SelfCheckoutCounter reactivate() {
        throw new IllegalStateException("Self-Check Counter does not rest");
    }

    @Override
    public String toString() {
        return "self-check " + this.getServID();
    }
}
