package cs2030.simulator;

public class ArrivesEvent extends Event {
    public ArrivesEvent(Customer cust) {
        super(cust.getArrTime(), cust, State.ARRIVES, null);
    }

    public Event handleArrivesAndServed(Server server, double servTime) {
        return new ServedEvent(this.getTime(), this.getCust(), server, servTime);
    }

    public Event handleArrivesAndWaits(Server server) {
        if (server.isSelfCheck()) {
            SelfCheckoutCounter newServer = (SelfCheckoutCounter) server;
            server = newServer.getQueueLocation();
        }
        return new WaitsEvent(this.getTime(), this.getCust(), server);
    }       

    public Event handleLeaves() {
        return new LeavesEvent(this.getTime(), this.getCust());
    }   

    @Override
    public Event execute(Server server, LogBook log, RandomGenerator rg, double restProb) {
        
        if (server == null) {
            return this.handleLeaves();
        }
        if (server.canServe(this.getTime())) {
            return handleArrivesAndServed(server, rg.genServiceTime());
        
        } else if (server.canQueue()) {
            return handleArrivesAndWaits(server);
        
        } else {
            return this.handleLeaves();
        }
   }
}
