package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.Comparator;

/** 
 * A Priority Queue of Events. 
 */ 
public class EventQueue extends PriorityQueue<Event> {
    public EventQueue(int size, Comparator<Event> comparer) {
        super(size, comparer);
    }

    public void updateQueue(Staff servers, LogBook log, RandomGenerator rg, double restProb) {
        EventQueue pq = this;
        Event event = pq.poll();
        Event newEvent;

        Customer cust = event.getCust();
        Comparator<Server> sorter;

        if (cust != null) {
            if (cust.isGreedy()) {
                sorter = new SortByQueueLength();
            } else {
                sorter = new SortByAvailability();
            }
            servers.sort(sorter);
        }

        Server server = servers.peek();
        if (event.getServer() != null) {
            server = servers.getServer(event.getServer().getServID());
        }

        newEvent = event.execute(server, log, rg, restProb);

        if (newEvent != null) {
            pq.add(newEvent);
        }
    }
}
