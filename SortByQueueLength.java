package cs2030.simulator;
import java.util.Comparator;

class SortByQueueLength implements Comparator<Server> {
    
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
            if (s1.getQueue().size() < s2.getQueue().size()) {
                return -1; 

            } else if (s1.getQueue().size() > s2.getQueue().size()) {
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
