package cs2030.simulator;

public class LogBook {
    private int totalCust;
    private int numServed;
    private double totalWait;

    public LogBook() {
        this.totalCust = 0;
        this.numServed = 0;
        this.totalWait = 0;
    }
    
    /** 
     * Updates the total wait time of the all the Customers in the system.
     * @param totalWait the total waiting time for a particular Customer
     */
    public void addTotalWait(double totalWait) {
        this.totalWait += totalWait;
    }

    /** 
     * Updates the total number of Customers that were served in the system.
     * @param totalCust the total number of Customers
     */
    public void initTotalCust(int totalCust) {
        this.totalCust = totalCust;
    }

    public void addNumServed() {
        this.numServed++;
    }
    /** 
     * Returns the total number of Customers that were served.
     * @return total number of served Customers
     */ 
    public int getNumServed() {
        return this.numServed;
    }

    /** 
     * Returns the average waiting time of the served Customers.
     * @return average waiting time of the Customers that were served
     */
    public double getAveWait() {
        if (this.totalWait == 0 && this.numServed == 0) {
            return 0.0;
        }
        return this.totalWait / this.numServed;
    }

    /** 
     * Returns the number of Customers who were not served.
     * @return number of Customers who left without being served
     */
    public int getNumUnserved() {
        return this.totalCust - this.numServed;
    }

    /** 
     * Returns the total number of Customers in the system.
     * @return total number of Customers
     */
    public double getTotalCust() {
        return this.totalCust;
    }

    /** 
     * Returns the total waiting time of Customers who were served.
     * @return total waiting time of served Customers
     */
    public double getTotalWait() {
        return this.totalWait;
    }
}
