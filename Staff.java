package cs2030.simulator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

/** 
 * A Priority Queue of Servers. 
 */
public class Staff extends ArrayList<Server> {

    /** 
     * Initialises a new Staff class to a specified initial capacity,
     * arranged according to the order specified by a Comparator.
     * @param size sets the capacity of Staff, i.e. the number of servers
     * @param comparer sets the Comparator used to order the servers in Staff
     */
    public Staff(int size) {
        super(size);
    }

    public Staff() {
        super();
    }

    public Server peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.get(0);
    }
    
    public Server getServer(int servID) {
        Server result = null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getServID() == servID) {
                result = this.get(i);
            }
        }
        return result;
    }
    
    /** 
     * Updates the status of servers in Staff, 
     * according to the state of a particular event.
     * @param event is used to evaluate the new status of servers in Staff
     * @param log tracks the service statistics of the program
     */
    public void updateServers(Event event, RandomGenerator rg) {
        Staff servers = this;  
        Server server = event.getServer(); 

        if (event.getState() == State.SERVED) {
            servers.remove(server);
            servers.add(server.serve(event.getCust(), ((ServedEvent)event).getCompleteTime()));

        } else if (event.getState() == State.WAITS) {
            server = this.getServer(server.getServID());
            servers.remove(server);
            servers.add(server.queue(event.getCust()));

        } else if (event.getState() == State.DONE) {
            servers.remove(server);
            servers.add(server.finish());
        
        } else if (event.getState() == State.SERVER_REST) {
           servers.remove(server);
           ServerRestEvent restEvent = (ServerRestEvent)event;
           double restPeriod = restEvent.getRestPeriod();
           servers.add(server.rest(restPeriod));
        
        } else if (event.getState() == State.SERVER_BACK) {
            servers.remove(server);
            servers.add(server.reactivate());
        }
    }
}
