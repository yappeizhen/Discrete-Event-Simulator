package cs2030.simulator;

import java.util.Comparator;

/** This class implements an event comparator which will aid 
 * in sorting events by increasing event timing.
 */
public class SortByEvent implements Comparator<Event> {

    /** This method sorts events by increasing order of event timing
     * If two events have the same time of occurrence, 
     * then the one with the customer of a smaller ID number will occur first.
     * @param e1 represents event 1 to be compared to event 2
     * @param e2 represents event 2 to be compared to event 1
     * @return the method returns 1 if e1 should occur before e2,
     *     0 if they should occur at the same time, 
     *     and -1 if e2 should occur before e1.
     */    
    public int compare(Event e1, Event e2) {
        if (Math.abs(e1.getTime() - e2.getTime()) < 1E-15) {
            if (e1.getCust().getID() > e2.getCust().getID()) {
                return 1;
            } else if (e1.getCust().getID() < e2.getCust().getID()) {
                return -1;
            } else {
                return 0;
            }
        }
        if (e1.getTime() > e2.getTime()) {
            return 1;
        }
        return -1;
    }
}
