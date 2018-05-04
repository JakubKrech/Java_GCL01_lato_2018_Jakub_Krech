/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;  // uzywane w myThreadFactory linia 49

/**
 *
 * @author Kuba
 */
public class Worker {
    
    public int threadAmmount;
    String workerName;
    ExecutorService executor;
    public NewTask[] taskTab = new NewTask[20];
    public String[] taskNameTab = new String[20];
    public int tabCounter = 0;
    public int tabCounter2 = 0;
    public NewWorkerListener nl;
    public boolean isActive = false;
    
    Worker(String wn, int t) {        
        threadAmmount = t;
        //this.executor = Executors.newFixedThreadPool(threadAmmount, new YourThreadFactory());
        this.executor = new ThreadPoolExecutor(threadAmmount, threadAmmount, 0L, 
                                            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(15), new YourThreadFactory());
        this.workerName = wn;
        setListener();
    }
    
    Worker(String wn) {
        threadAmmount = Runtime.getRuntime().availableProcessors();
        System.out.println(threadAmmount);
        this.executor = Executors.newFixedThreadPool(threadAmmount, new YourThreadFactory());
        this.workerName = wn;
        setListener();
    }
    
    class YourThreadFactory implements ThreadFactory {
        private final AtomicInteger counter = new AtomicInteger(1);
        
        @Override
        public Thread newThread(Runnable r) {
        return new Thread(r, "Worker" + workerName + "Thread[" + counter.getAndIncrement() + "]");
        }
    }
 
    
    public void enqueueTask(NewTask task, String taskName) {
        // dodającą kolejne zadania do wykonania
        taskTab[tabCounter] = task;
        taskNameTab[tabCounter] = taskName;
        
        if(isActive == true) {
            Runnable task1 = new NewThread(taskTab[tabCounter], taskNameTab[tabCounter]);
            executor.execute(task1);
        }
        tabCounter++;
    }
    
    public void start() {
        if(isActive == false) {
            isActive = true;
            if(taskTab[0].activated == false) {
                for(int i = 0; i < tabCounter; i++) {  //zmieniam w kazdej instancji taska activated zeby wznowic wykonywanie
                    taskTab[i].activated = true;
                }
            }
            /*synchronized (executor) {
                executor.notify();                  /////
            }*/
            
            nl.onWorkerStarted();
            for (; tabCounter2 < tabCounter; tabCounter2++) {
                Runnable task = new NewThread(taskTab[tabCounter2], taskNameTab[tabCounter2]);
                executor.execute(task);
              }
            //executor.shutdown();
            //while (!executor.isTerminated()) {}    // pauzuje dalsze wykonywanie reszty kodu aż executor skonczy prace
            
            
        }
        else System.out.println(workerName + " ALREADY RUNNING!!");
    }
    
    public void stop() {
        // wysyła sygnał przerwania Worker’a; wykonanie tej metody ma spowodować
        // bezpieczne przerwanie wątku – tzn. nie wolno przerwać task’u z poziomu
        // wątku w trakcie jego wykonywania; jako ostatnia operacja w wątku 
        // wykonuje się onWorkerStoped
        for(int i = 0; i<tabCounter; i++) {  //zmieniam w kazdej instancji taska activated zeby przerwac wykonywanie petli w nich
            taskTab[i].activated = false;
        }
        
        /*synchronized (executor) {
            try{                                    /////
                executor.wait();
            } 
            catch (InterruptedException e){
                e.printStackTrace();
            }                                       /////
        }*/
        isActive = false;
        nl.onWorkerStopped();    
    }
    
    public void shutDownWorker() {
        isActive = false;
        System.out.println("--------WORKER SHUTDOWN -- ENDING PROGRAM--------");
        executor.shutdown();
    }
    
    
    public void setListener() {
        // przypisuje zestaw event’ów wykonywanych przez Worker
        nl = new NewWorkerListener();
    }
    
    public boolean isStarted() {
        // informuje o tym czy Worker jest obecnie uruchomiony
        return true;
    }
    
    public boolean isWorking() {
        // informuje o tym czy Worker wykonuje obecnie jakieś zadania
        return true;
    }
    
    public void sleepFor(int i) {
        try { 
            Thread.sleep(i);
        }
        catch(InterruptedException ex){ 
            Thread.currentThread().interrupt();
        }
    }
}
