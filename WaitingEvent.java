package cs2030.simulator;

public class WaitingEvent extends Event {
    public WaitingEvent(double time, Customer cust, Server server) {
        super(time, cust, State.WAITING, server);
    }

    public WaitingEvent handleContinueWait(Server server) {
        return new WaitingEvent(server.getTimeAvail(), this.getCust(), server);
    }

    public ServedEvent handleCompleteWait(Server server, RandomGenerator rg) {
        return new ServedEvent(this.getTime(), this.getCust(), this.getServer(), rg.genServiceTime());
    }

    public boolean canBeServed(Server server) {
        if (this.getTime() >= server.getTimeAvail() &&
            server.getQueue().isNext(this.getCust()) && server.isWorking()) {
            return true;
        }
        return false;
    }

    @Override
    public Event execute(Server server, LogBook log, RandomGenerator rg, double restProb) {
        if (this.canBeServed(server)) {
            return handleCompleteWait(server, rg);
        }
        return handleContinueWait(server);
    }

    @Override 
    public String toString() {
        return "";
    }
}
