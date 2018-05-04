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
public interface WorkerListener {
    
    public void onWorkerStarted();
    public void onWorkerStopped();
    public void onTaskStarted(int taskNumber, String taskName);
    public void onTaskCompleted(int taskNumber, String taskName);
    
}
