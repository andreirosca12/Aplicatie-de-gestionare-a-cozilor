package business_logic;

import model.Server;
import model.Task;

import java.util.LinkedList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer,int strateg) {
        this.maxNoServers=maxNoServers;
        this.maxTasksPerServer=maxTasksPerServer;
        servers=new LinkedList<Server>();
        if(strateg==1)
            strategy=new ConcreteStrategyTime();
        else strategy=new ConcreteStrategyQueue();
        for (int i = 1; i <= maxNoServers; i++) {
            Server s = new Server();
            servers.add(s);
            Thread t = new Thread(s);
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteStrategyTime();
        if (policy== SelectionPolicy.SHORTEST_QUEUE)
            strategy=new ConcreteStrategyQueue();
    }

    public void dispatchTask(Task t)
    {
        strategy.addTask(servers,t);
    }

    public int sumaWTime()
    {
        int sum=0;
        for(Server s:servers)
        {
            sum+=s.getSumWaitingTime();
        }
        return sum;
    }
    public int sumaSTime()
    {
        int sum=0;
        for(Server s:servers)
        {
            sum+=s.getSumServiceTime();
        }
        return sum;
    }
    public int taskTime()
    {
        int sum=0;
        for(Server s:servers)
        {
            sum+=s.tasksSize();
        }
        return sum;
    }
    public int terminare()
    {
        int ok=1;
        for(Server s:servers)
        {
            if(!s.isEmpty())
                return 0;
        }
        return 1;
    }
    public String print()
    {
        int i=1;
        String s2="";
        for(Server s:servers)
        {
           s2=s2+"Queue "+i +": "+s+"\n";
           i++;
        }
        return s2;
    }

}