package cs2030.simulator;

import java.util.Comparator;

/** This class implements server comparator which will aid
 * in sorting servers by availability and increasing server ID number.
 */
public class SortByAvailability implements Comparator<Server> {

    /** This method sorts events by the time at which they are next available 
     * Availability depends on 1) presence of a queue, and 2) next time available 
     * If two events have the same availability,
     * then the one with the customer of a smaller ID number will occur first.
     * @param s1 represents server 1 to be compared to server 2
     * @param s2 represents server 2
     * @return the method returns 1 if s1 should occur before s2,
     *     0 if they should occur at the same time,
     *     and -1 if s2 should occur before s1.
     */

    public int compare(Server s1, Server s2) {

        if (s1.getCust() == null 
                && s1.getQueue().isEmpty()
                && s1.isWorking()
                && (s2.getCust() != null || !s2.isWorking())) {
            return -1;

        } else if ((s1.getCust() != null || !s1.isWorking())
                && s2.isWorking() 
                && s2.getQueue().isEmpty()
                && s2.getCust() == null) {
            return 1;

        } else {
            if (s1.canQueue() && !s2.canQueue()) {
                return -1;

            } else if (!s1.canQueue() && s2.canQueue()) {
                return 1;
            }
        }

        if (s1.getServID() < s2.getServID()) {
            return -1;
        } else if (s1.getServID() > s2.getServID()) {
            return 1;
        } else {
            return 0;
        }
    }
}
