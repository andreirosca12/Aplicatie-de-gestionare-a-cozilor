package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements  Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    private int sumServiceTime;
    private int sumWaitingTime;
    public Server(){
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
    }
    public void addTask(Task t) {
        try {
            tasks.put(t);
            waitingPeriod.getAndAdd(t.getServiceTime());
        } catch (InterruptedException e) {
            System.out.println("Task could not be added to the queue");
        }
    }
    public String toString()
    {
        String s = "";
        if(!tasks.isEmpty()) {
            for (Task t : tasks) {
                s += t;
            }
        }
        else s="(inchis)";
        return s;
    }
    public boolean isEmpty()
    {
        return tasks.isEmpty();
    }
    public void crestereWaitingTime()
    {
        for(Task t:tasks)
        {
            t.crestereWTime();
        }
    }
    public void run()
    {
          while(true) {
              try {
                  Task t = tasks.peek();
                  if(t!=null) {
                      int x = t.getServiceTime();
                      sumServiceTime+=t.getServiceTime();
                      for (int i = 0; i < x; i++) {
                          crestereWaitingTime();
                          Thread.sleep(1000);
                          t.scadereServiceTime();
                          waitingPeriod.decrementAndGet();
                      }
                      t = tasks.take();//trebuie pus in interiorul if ului deoarece daca nu se blocheaza
                      sumWaitingTime += t.getWaitingTime();
                  }
              } catch (InterruptedException e) {
                  System.out.println("Task could not be added to the queue");
              }
          }
    }
    public int getSumWaitingTime()
    {
        return sumWaitingTime;
    }
    public int getSumServiceTime()
    {
        return sumServiceTime;
    }
    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }
    public int tasksSize()
    {
        return tasks.size();
    }
    public int getSize() {
        return  tasks.size();
    }
}
