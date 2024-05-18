package model;

public class Task implements Comparable{
    private int arrivalTime;
    private int serviceTime;
    private int id;
    private int waitingTime;
    public Task(int arrivalTime, int serviceTime, int id)
    {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.id = id;
        this.waitingTime=0;
    }
    public int getId()
    {
        return id;
    }
    public int getArrivalTime()
    {
        return arrivalTime;
    }
    public int getServiceTime()
    {
        return serviceTime;
    }
    public int compareTo(Object x) {
        Task o=(Task)x;
        return Integer.compare(this.arrivalTime,o.arrivalTime);
    }
    public void scadereServiceTime()
    {
        this.serviceTime--;
    }
    public void crestereWTime()
    {
        this.waitingTime++;
    }
    public int getWaitingTime()
    {
        return this.waitingTime;
    }
    @Override
    public String toString() {
        return "(id:"+this.id+"  arival:"+this.arrivalTime+"  service:"+this.serviceTime+")";
    }
}
