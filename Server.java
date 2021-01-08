package cs2030.simulator;

import java.lang.String;
import java.util.ArrayList;

/** An immutable class which represents a Server,
 * a Server serves up to one Customer and has up to one Customer in queue.
 */
public class Server {
    private static final double initTimeAvail = 0.0;

    private final int servID;
    private final Customer customer;
    private final double timeAvail;
    private final CustQueue queue; 
    private boolean isWorking;
    
    public Server(int servID, int maxQueueLength) {
        this.servID = servID;
        this.customer = null;
        this.timeAvail = initTimeAvail;
        this.queue = new CustQueue(maxQueueLength);
        this.isWorking = true;
   }

    /** Updates a Server with the Customer to serve
     * and its next time available, queue is cleared to null.
     * @param servID sets the Server's unique ID to servID
     * @param customer sets the Customer to customer
     * @param timeAvail sets the next time available to timeAvail
     */
    public Server(int servID, Customer customer, double timeAvail, CustQueue queue) {
        this.servID = servID;
        this.customer = customer;
        this.timeAvail = timeAvail;
        this.queue = queue;
        this.isWorking = true;
   }

   public Server(int servID, Customer customer, double timeAvail, CustQueue queue, boolean isWorking) {
       this.servID = servID; 
       this.customer = customer;
       this.timeAvail = timeAvail;
       this.queue = queue;
       this.isWorking = isWorking;
   }
    
    /** Returns the Server's ID number.
     * @return Server's ID number
     */
    public int getServID() {
        return this.servID;
    }

    /** Returns the Customer in the queue.
     * @return Customer in queue
     */
    public CustQueue getQueue() {
        return this.queue;
    }

    /** Returns the Customer being served.
     * @return Customer being served
     */
    public Customer getCust() {
        return this.customer;
    }

    /** Returns the next time available of the Server.
     * @return time of availability
     */
    public double getTimeAvail() {
        return this.timeAvail;
    }

    public boolean isWorking() {
        return this.isWorking;
    }

    /** Evaluates whether a Server can serve in a particular event. 
     * @param event to check if the Server can serve in this event
     * @return returns a boolean value TRUE if the Server is able to serve, 
     *     and FALSE otherwise
     */
    public boolean canServe(double eventTime) {
        if (eventTime < this.timeAvail
            || this.getCust() != null
            || !this.isWorking()
            || !this.getQueue().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean canQueue() {
        return !this.getQueue().isFull();
    }

    public Server serve(Customer cust, double newTime) {
        this.queue.remove(cust);
        return new Server(this.servID, cust, newTime, this.getQueue());
    }
    
    /** Places the Customer from an event in the server queue.
     * @param event the event for which the Customer is queueing
     * @return a new Server with its updated queue
     */
    public Server queue(Customer customer) {
        this.queue.add(customer);
        return new Server(this.servID, this.customer, this.timeAvail, this.getQueue(), this.isWorking());
    }

    /** Instructs a Server to finish up an event, 
     * clears Customer in service to null,
     * and serves the customer waiting in the queue (if applicable).
     * @return new Server with updated customer and queue
     */
    public Server finish() {
        return new Server(this.servID, null, this.getTimeAvail(), this.queue);
    }

    public Server rest(double restTime) {
        return new Server(this.getServID(), null, this.getTimeAvail() + restTime, this.queue, false);
    }

    public Server reactivate() {
        return new Server(this.getServID(), this.getCust(), this.getTimeAvail(), this.queue);
    }

    public boolean isSelfCheck() {
        return false;
    }

    @Override 
    public boolean equals(Object obj) {
        if (obj instanceof Server) {
            Server other = (Server) obj;
            if (other.getServID() == this.getServID()) {
                return true;
            }
        }
        return false;
    }
    
    /*@Override 
    public String toString() {
        return this.getServID() + " " + this.customer + " " + this.timeAvail + " " + this.isWorking + " " + this.getQueue();
    }*/

    @Override
    public String toString() {
        return "server " + this.getServID();
    }
}
