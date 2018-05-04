package concurrency;

public class Concurrency {

    /* TO DO
        - glowny watek programu czekajacy na zakonczenie wykonywania taskow (ma nie zuzywac procka)
        - interrupty do przerywania wykonywanych zadan?
        - zadanie 100% procka, 0% procka, Thread.yeld() + komentarz jak wplywa ten yeld na zuzycie procka
    */
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Worker worker1 = new Worker("ONE", 3);
        //Worker worker1 = new Worker("ONE"); // creating worker without specifying ammount of threads
        
        NewTask task1 = new NewTask("One");
        NewTask task2 = new NewTask("Two");
        NewTask task3 = new NewTask("Three");
        NewTask task4 = new NewTask("Four");
        NewTask task5 = new NewTask("Five");
        
        worker1.enqueueTask(task1, "One");
        worker1.enqueueTask(task2, "Two");
        worker1.enqueueTask(task3, "Three");
        worker1.enqueueTask(task4, "Four");
        worker1.enqueueTask(task5, "Five");
        
        worker1.start();
        //worker1.start();     
        
        /*worker1.sleepFor(5000);
        worker1.stop();
        worker1.sleepFor(3000);
        worker1.start();*/
        
        NewTask task6 = new NewTask("Six");
        NewTask task7 = new NewTask("Seven");
        NewTask task8 = new NewTask("Eight");
        NewTask task9 = new NewTask("Nine");
        NewTask task10 = new NewTask("Ten");
        
        worker1.enqueueTask(task6, "Six");
        worker1.enqueueTask(task7, "Seven");
        worker1.enqueueTask(task8, "Eight");
        worker1.enqueueTask(task9, "Nine");
        worker1.enqueueTask(task10, "Ten");
        
        worker1.sleepFor(15000);
        worker1.stop();
        
        worker1.sleepFor(15000);
        //worker1.shutDownWorker();
        
        //System.out.println("\nKONIEC GLOWNEGO WATKU\n");
        
        
    }
    
}
