package obslugaplikowistrumieni;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExtendedSystemCache implements java.io.Serializable
{
    
    private HashMap<Parameter, Double> cache = new HashMap<>();
    
    ExtendedSystemCache() {
        
    }
    
    public void put( double[] input, double output ) {
        this.cache.put( new Parameter( input ), output );
    }
    
    public Double get( double[] input ) {
        return this.cache.get( new Parameter( input ) );
    }
    
    public void print() {
        for (HashMap.Entry<Parameter, Double> entry : cache.entrySet()) {
                Parameter key = entry.getKey();
                
                System.out.print(String.valueOf(key.values[0]) + " ");
                for(int i = 1;i<key.values.length; i++) {
                    System.out.print(String.valueOf(key.values[i]) + " ");                    
                }
                Double value = entry.getValue();
                System.out.print(": " + String.valueOf(value) + " PRINTED \n");              
        }
    }
    
    private class Parameter implements java.io.Serializable {
        
        protected double[] values;
        
        public Parameter( double[] values ) {
            this.values = values;
        }
        
        @Override
        public int hashCode() {
            return 31 + Arrays.hashCode( this.values );
        }
        
        @Override
        public boolean equals( Object obj ) {
            if ( this == obj )
                return true;
            if ( obj == null )
                return false;
            if ( this.getClass() != obj.getClass() )
                return false;
            
            Parameter other = (Parameter) obj;
            
            if ( !Arrays.equals( this.values, other.values ) ) {
                return false;
            }
            return true;
        }
    }
    
    // wyeksportuje wszystkie przechowywane w mapie obiekty do pliku CSV
    void exportCSV( String path ) throws IOException {
        File file = new File(path);
        exportCSV(file);
    }
    void exportCSV( File file ) throws IOException {
        FileWriter fw = new FileWriter(file);  
        exportCSV(fw);
        fw.close();
    }
    void exportCSV( FileWriter fw ) throws IOException {
       
        try (FileWriter writer = fw) {
            for (HashMap.Entry<Parameter, Double> entry : cache.entrySet()) {
                Parameter key = entry.getKey();
                writer.append(String.valueOf(key.values[0]));
                for(int i = 1;i<key.values.length; i++) {
                    writer.append(',')
                          .append(String.valueOf(key.values[i]));
                    
                }
                    writer.append(',')
                          .append(entry.getValue().toString())
                          .append(System.getProperty("line.separator"));
            }
        } catch (IOException ex) {
                ex.printStackTrace(System.err);
        } 
    }
    
    // zaimportuje wszystkie rekordy z pliku CSV do mapy
    void importCSV( String path ) throws IOException {
        File file = new File(path);
        importCSV(file);
    }
    void importCSV( File file ) throws IOException {
        FileReader fw = new FileReader(file);  
        importCSV(fw);
        fw.close();
    }
    void importCSV( FileReader fw ) throws IOException {
        BufferedReader br = new BufferedReader(fw);
        
        String line = null;
          
        while((line=br.readLine())!=null){
            
            double[] tab = new double[10];
            
            String str[] = line.split(",");
            
            for(int i=0;i<10;i++){
                tab[i] = Double.parseDouble(str[i]);
                System.out.print(tab[i] + " ");
            }
            Double value = Double.parseDouble(str[10]);
            System.out.print(": " + value);
            cache.put(new Parameter(tab), value);
            System.out.print("  ADDED TO CACHE FROM CSV\n");
        }
        
    }
    
    // zapisze do pliku wszystkie pola znajdujące się w
    // ExtendedSystemCache wykorzystując mechanizm serializacji
    void serialize( String path ) throws IOException {
        FileOutputStream file = new FileOutputStream(path);
        serialize(file);
        file.close();
    }
    void serialize ( FileOutputStream fos ) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(fos);  
        serialize(oos);
        oos.close();
    }
    void serialize ( ObjectOutputStream oos ) throws IOException {
        oos.writeObject(cache);
    }
    
    // odczyta z pliku wszystkie pola znajdujące się w
    // ExtendedSystemCache wykorzystując mechanizm serializacji
    void deserialize( String path ) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        deserialize(fis);
        fis.close();
    }
    void deserialize( FileInputStream fis ) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(fis);  
        deserialize(ois);
        ois.close();
    }
    void deserialize( ObjectInputStream ois ) throws IOException {
        try {
            ois.readObject();
        } catch (ClassNotFoundException i) {
         i.printStackTrace();         
        }
    }

}