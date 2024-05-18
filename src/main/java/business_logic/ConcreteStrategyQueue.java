package business_logic;

import model.Server;
import model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        int min = Integer.MAX_VALUE;
        Server s = null;
        for (Server server:servers) {
            if (server.getSize()< min) {
                min = server.getSize();
                s = server;
            }
        }
        if (s != null) {
            s.addTask(t);
        }
    }
}
