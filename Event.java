package cs2030.simulator;

/** The class Event details the time, state and the customer involved in an event.
 */
public abstract class Event {
    private final double time;
    private final Customer cust;
    private final State state;
    private final Server server;
    
    /** This constructor allows changes to the time and the state of event for a customer. 
     * @param time sets the event time to time
     * @param cust sets the customer related to the event to cust
     * @param state sets the state of the event to state
     */
    public Event(double time, Customer cust, State state, Server server) {
        this.time = time;
        this.cust = cust;
        this.state = state;
        this.server = server;
    }

    /** Returns the time of event.
     * @return event time
     */
    public double getTime() {
        return this.time;
    }

    /** Returns the customer in the event.
     * @return customer in this particular event
     */
    public Customer getCust() {
        return this.cust;
    }

    /** Returns the service state of the event.
     * @return state of event
     */ 
    public State getState() {
        return this.state;
    }

    public Server getServer() {
        return this.server;
    }
 
    abstract Event execute(Server server, LogBook log, RandomGenerator rg, double restProb);

    public double getServiceTime() {
        return 0.0;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.time) + " " + this.cust + " " + 
            this.state.name().toLowerCase();
    }
}
