package obslugaplikowistrumieni;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class ObslugaPlikowIStrumieni {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException  {
        
        ScatterSystem system = new ScatterSystem(); // można umieścić wewnątrz dowolną funkcję matematyczną
        ExtendedSystemCache cache = new ExtendedSystemCache();   
        
        /*double[] input = new double[10];
        for(int i = 0;i < 10; i++) {
            input[i] = ThreadLocalRandom.current().nextInt(0, 9 + 1);
            System.out.print(input[i] + " ");
        }
        System.out.print("\n");
        
        Double output;
        
        output =  cache.get(input);
            
        if(output == null) {
            output = system.sum(input);
            cache.put(input, output);
            System.out.println(output + " CACHED");
        }
        else System.out.println(output + " LOADING FROM CACHE");
        
        //System.out.println(cache.get(input) + "\n\n");
        
        cache.exportCSV("PLIKI CSV/plik1.csv");
        //-----------------------------------
        double[] input2 = new double[10];
        for(int i = 0;i < 10; i++) {
            input2[i] = ThreadLocalRandom.current().nextInt(0, 9 + 1);
            System.out.print(input2[i] + " ");
        }
        System.out.print("\n");
        
        Double output2;
        
        output2 =  cache.get(input2);
            
        if(output2 == null) {
            output2 = system.sum(input2);
            cache.put(input2, output2);
            System.out.println(output2 + " CACHED");
        }
        else System.out.println(output2 + " LOADING FROM CACHE");
        
        cache.exportCSV("PLIKI CSV/plik1.csv");*/
        
        cache.importCSV("PLIKI CSV/plik1.csv");
        cache.print();
        
        //cache.serialize("PLIKI CSV/serialize.ser");
        //cache.deserialize("PLIKI CSV/serialize.ser");
        //cache.print();
        
        System.out.println("\n");
    }          
}
