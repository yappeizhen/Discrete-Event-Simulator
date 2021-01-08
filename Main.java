import cs2030.simulator.Simulator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int baseSeed = sc.nextInt();
        int numServers = sc.nextInt();
        int numSelfCheckout = sc.nextInt();
        int maxQueueLength = sc.nextInt();
        int numCust = sc.nextInt();
        double arrRate = sc.nextDouble();
        double servRate = sc.nextDouble();
        double restRate = sc.nextDouble();
        double restProb = sc.nextDouble();
        double greedyProb = sc.nextDouble();
        sc.close();

        Simulator simulator = new Simulator(baseSeed, numServers, numSelfCheckout, maxQueueLength, numCust, arrRate, servRate, restRate, restProb, greedyProb);
        simulator.initServers(numServers, maxQueueLength, numSelfCheckout);
        simulator.initQueue(numCust);
        simulator.simulate();
    }
}   
