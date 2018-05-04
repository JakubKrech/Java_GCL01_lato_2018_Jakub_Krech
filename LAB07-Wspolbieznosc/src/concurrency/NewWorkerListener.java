package concurrency;

public class NewWorkerListener {
    
    public synchronized void onWorkerStarted() {
        System.out.println("-------- WORKER  STARTS --------");
    }
    
    public synchronized void onWorkerStopped() {
        System.out.println("-------- WORKER STOPPED --------");
    }
    
    public synchronized void onTaskStarted(String taskName) { 
        System.out.println(Thread.currentThread().getName() + " >>>>>>  Task [" + taskName + "] Started 0%");
    }
    
    public synchronized void onTaskCompleted(String taskName) {
        System.out.println(Thread.currentThread().getName() + " >>>>>>  Task [" + taskName + "] Finished");
    }
    
}
