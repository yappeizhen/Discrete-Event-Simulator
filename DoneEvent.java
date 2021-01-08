package cs2030.simulator;

public class DoneEvent extends Event {
    public DoneEvent(double time, Customer cust, Server server) {
        super(time, cust, State.DONE, server);
    }

    public boolean toRest(RandomGenerator rg, double restProb) {
        double result = rg.genRandomRest();
        return result < restProb;
    }

    public ServerRestEvent handleDone(Server server, RandomGenerator rg, double restProb) {
        if (!server.isSelfCheck() && toRest(rg, restProb)) {
            return new ServerRestEvent(this.getTime(), server);
        } else {
            return null;
        }
    }

    @Override
    public Event execute(Server server, LogBook log, RandomGenerator rg, double restProb) { 
        return handleDone(server, rg, restProb);
    }

    @Override
    public String toString() {
        return super.toString() + " serving by " + this.getServer();
    }
}
