package cs2030.simulator;

public class ServerBackEvent extends Event {
    public ServerBackEvent(double time, Server server) {
        super(time, null, State.SERVER_BACK, server);
    }

    @Override
    public Event execute(Server server, LogBook log, RandomGenerator rg, double restProb) {
        return null;
    }

    @Override 
    public String toString() {
        return "";
    }
}
