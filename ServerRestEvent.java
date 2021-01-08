package cs2030.simulator;

public class ServerRestEvent extends Event {
    public double restPeriod;

    public ServerRestEvent(double time, Server server) {
        super(time, null, State.SERVER_REST, server);
        this.restPeriod = 0.0;
    }

    public double getRestPeriod() {
        return this.restPeriod;
    }

    @Override
    public Event execute(Server server, LogBook log, RandomGenerator rg, double restProb) {
        this.restPeriod = rg.genRestPeriod();
        return new ServerBackEvent(this.getTime() + this.restPeriod, server);
    }

    @Override
    public String toString() {
        return "";
    }
}
