package business_logic;

import model.Server;
import model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    public void addTask(List<Server> servers, Task t) {
        int min = Integer.MAX_VALUE;
        Server s = null;
        for (Server server:servers) {
            if (server.getWaitingPeriod() < min) {
                min = server.getWaitingPeriod();
                s = server;
            }
        }
        if (s != null) {
            s.addTask(t);
        }
    }
}
