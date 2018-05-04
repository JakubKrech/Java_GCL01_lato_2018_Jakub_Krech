package concurrency;

public class NewThread implements Runnable {
  
    public NewTask task;
    String taskName;
    public NewWorkerListener nl = new NewWorkerListener();
    
    public NewThread(NewTask nt, String i){
        this.task = nt;
        this.taskName = i;
    }

    @Override
    public void run() {
        
        nl.onTaskStarted(taskName);
        //System.out.println(Thread.currentThread().getName() + " >>>>>>  Task [" + taskName + "] Started 0%");
        
        try {
            task.run(taskName);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        nl.onTaskCompleted(taskName);
        //System.out.println(Thread.currentThread().getName() + " >>>>>>  Task [" + taskName + "] Finished");
    }   
}
