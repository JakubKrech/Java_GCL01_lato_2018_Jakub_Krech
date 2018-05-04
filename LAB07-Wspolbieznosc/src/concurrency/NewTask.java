/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrency;

/**
 *
 * @author Kuba
 */
public class NewTask implements Task{
    
    public int taskComplexity = 10;
    public String taskName;
    public boolean activated;

    
    NewTask(String tn) {
        taskName = tn;
        activated = true;
    }
    
    @Override
    public void run(String taskNa) throws InterruptedException {
        for(int i = taskComplexity; i > 0 == true; i--) {
            System.out.println(String.format("%-16s %5s" , "~~~ Task[" + taskName + "] ", (100 - ((i-1)*100)/taskComplexity) + "% "));
            
            Thread.sleep(1000);
            while (!activated) {
                try { 
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex){ 
                    Thread.currentThread().interrupt();
                }
            }
        }
        if(activated == false) System.out.println("~~~ Task[" + taskName + "] DEACTIVATED!");
    }    
}
