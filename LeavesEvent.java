package cs2030.simulator;

public class LeavesEvent extends Event {
    public LeavesEvent(double time, Customer cust) {
        super(time, cust, State.LEAVES, null);
    }

    @Override
    public Event execute(Server server, LogBook log, RandomGenerator rg, double restProb) {
        return null;
    }
}
