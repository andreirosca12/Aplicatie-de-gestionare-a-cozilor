package business_logic;

import model.Task;
import view.Interface;

import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable{
    public int timeLimit ;
    public int maxProcessingTime ;
    public int maxArrivalTime ;
    public int minArrivalTime ;
    public int minProcessingTime ;
    public int numberOfServers ;
    public int numberOfClients ;
    public int currentTime;
    public int maxTime;
    public int maxSize;
    public FileWriter writer ;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private List<Task> generatedTasks;

    private Interface form;
    public SimulationManager(){
        this.generatedTasks=new LinkedList<Task>();
        this.form = new Interface();
        try {
            writer = new FileWriter("afisare.txt");
            System.out.println("S-a creat fisierul");
        } catch (IOException e) {
            e.printStackTrace();
        }
        buton();
    }
    private void buton() {
        form.getButon().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTime=0;
                timeLimit = form.getTime();
                minArrivalTime=form.getMinArrivalTime();
                maxProcessingTime = form.getMaxProcessingTime();
                minProcessingTime = form.getMinProcessingTime();
                numberOfServers = form.getNumberOfServers();
                numberOfClients = form.getNumberOfClients();
                maxArrivalTime = form.getMaxArrivalTime();
                int x= form.selectedRadio1();
                scheduler=new Scheduler(numberOfServers,numberOfClients,x);
                generateNRandomTasks();
                Thread t=new Thread(SimulationManager.this);
                t.start();
            }
        });
    }
    private void generateNRandomTasks()
    {
        Random r =new Random();
        for(int i=1;i<=numberOfClients;i++) {
            int processing = r.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;
            int arriving = r.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            generatedTasks.add(new Task(arriving,processing,i));
        }
        Collections.sort(generatedTasks);
    }
    public int getTimeLimit() {
        return timeLimit;
    }
    public int getNumberOfClients() {
        return numberOfClients;
    }
    public void afisareFisiere(String s)
    {
        try {
            writer.write(s);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
   public void waitingTime()
   {
       int l=0;
       if(!generatedTasks.isEmpty()) {
           l=generatedTasks.size();
       }
       form.afisareWTime(""+(float)scheduler.sumaWTime()/(float)(numberOfClients-l));
       try {
           writer.write("Avg Waiting Time :"+(float)scheduler.sumaWTime()/(float)(numberOfClients-l)+"\n");
           writer.flush();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }
   public void serviceTime(){
       int l=0;
       if(!generatedTasks.isEmpty()) {
           l=generatedTasks.size();
       }
       form.afisareSTime(""+(float)scheduler.sumaSTime()/(float)(numberOfClients-l));
       try {
           writer.write("Avg Service Time :"+(float)scheduler.sumaSTime()/(float)(numberOfClients-l)+"\n");
           writer.flush();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }
    public void maxTraffic(){
        form.afisareMaxTrafficTime(""+maxTime);
        try {
            writer.write("Max Traffic Time :"+maxTime);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void run()
    {
       while(currentTime<timeLimit) {
           form.afisareTimp("" + currentTime);
           afisareFisiere("Time " + currentTime + "\n");
           if (!generatedTasks.isEmpty()) {
               Iterator<Task> iterator = generatedTasks.iterator();
               while (iterator.hasNext()) {
                   Task t = iterator.next();
                   if (t.getArrivalTime() == currentTime) {
                       scheduler.dispatchTask(t);
                       iterator.remove();
                   }
               }
           }
           if(maxSize<scheduler.taskTime()) {
               maxSize = scheduler.taskTime();
               maxTime = currentTime;}
           String s3 = scheduler.print();
           form.afisareCozi(s3);
           afisareFisiere(s3);
           if(scheduler.terminare()==1 && generatedTasks.isEmpty())
               break;
           currentTime++;
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       this.waitingTime();
       this.serviceTime();
       this.maxTraffic();
    }
    public static void main(String args[])
    {
        SimulationManager x=new SimulationManager();
    }
}
