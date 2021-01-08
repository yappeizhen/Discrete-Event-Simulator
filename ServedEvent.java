package cs2030.simulator;

public class ServedEvent extends Event {
    private final double servTime;

    public ServedEvent(double time, Customer cust, Server server, double servTime) {
        super(time, cust, State.SERVED, server);
        this.servTime = servTime;
    }

    @Override
    public double getServiceTime() {
        return this.servTime;
    }

    public double computeWaitTime() {
        return this.getTime() - this.getCust().getArrTime();
    }

    public double getCompleteTime() {
        return this.getTime() + this.servTime;
    }

    public Event handleServed() {
        return new DoneEvent(this.getCompleteTime(), this.getCust(), this.getServer());
    }

    @Override
    public Event execute(Server server, LogBook log, RandomGenerator rg, double restProb) {
        log.addNumServed();
        log.addTotalWait(this.computeWaitTime());
        return this.handleServed();
    }

    @Override 
    public String toString() {
        return super.toString() + " by " + this.getServer();
    }
}
