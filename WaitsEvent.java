package cs2030.simulator;
public class WaitsEvent extends Event {
    public WaitsEvent(double time, Customer cust, Server server) {
        super(time, cust, State.WAITS, server);
    }
 
    @Override 
    public Event execute(Server server, LogBook log, RandomGenerator rg, double restProb) {
      return new WaitingEvent(server.getTimeAvail(), this.getCust(), server);
    }

    @Override
    public String toString() {
        return super.toString() + " to be served by " + this.getServer();
    }
}
