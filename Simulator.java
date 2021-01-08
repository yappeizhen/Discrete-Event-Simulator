package cs2030.simulator;

public class Simulator {
    private final RandomGenerator rg;
    private final double restProb;
    private final double greedyProb;
    private final LogBook log;
    private Staff servers;
    private EventQueue pq;

    public Simulator(int baseSeed, int numServers, int numSelfCheckout, int maxQueueLength, 
        int numCust, double arrRate, double servRate, double restRate, 
        double restProb, double greedyProb) {
        this.rg = new RandomGenerator(baseSeed, arrRate, servRate, restRate);
        this.restProb = restProb;
        this.greedyProb = greedyProb;
        this.log = new LogBook();

        if (numServers == 0) {
            this.servers = new Staff();
        } else {
            this.servers = new Staff(numServers);
        }
        SortByEvent eventSorter = new SortByEvent();
        this.pq = new EventQueue(numCust, eventSorter);
    }

    public void initServers(int numServers, int maxQueueLength, int numSelfCheck) {
        int totalServers = numServers + numSelfCheck;
        int index = 1;
        while(index <= numServers) {
            this.servers.add(new Server(index, maxQueueLength));
            index++;
        }
        if (numSelfCheck > 0) {
            this.servers.add(new SelfCheckoutCounter(index, maxQueueLength));
            index++;
        }
        while(index <= totalServers) {
            this.servers.add(new SelfCheckoutCounter(index));
        index++;
        }
    }   

    public boolean isGreedy() {
        return rg.genCustomerType() < greedyProb;
    }

    public void initQueue(int numCust) {
        double currTime = 0.0;
        for (int i = 1; i <= numCust; i++) {
            Customer cust = new TypicalCustomer(i, currTime);
            
            if (isGreedy()) {
                cust = new GreedyCustomer(i, currTime);
            } 
            
            this.pq.add(new ArrivesEvent(cust));
            currTime += this.rg.genInterArrivalTime();
        }
    }

    public void simulate() {
        log.initTotalCust(pq.size());

        while (pq.size() != 0) {
            Event event = pq.peek();
            
            if (event.getState() != State.SERVER_REST 
                && event.getState() != State.SERVER_BACK
                && event.getState() != State.WAITING) { 
                System.out.println(event);
            }

            pq.updateQueue(servers, log, rg, restProb);
            if (pq.peek() == null) {
                break;
            }
            servers.updateServers(pq.peek(), rg);
            //System.out.println(servers); // For debugging
        }

        System.out.println("[" + String.format("%.3f", log.getAveWait())
                + " " + log.getNumServed() + " " + log.getNumUnserved() + "]");
    }
}
