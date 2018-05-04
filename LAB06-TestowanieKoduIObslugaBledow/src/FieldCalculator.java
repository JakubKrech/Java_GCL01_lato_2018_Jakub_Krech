public class FieldCalculator 
{    
    public double calculateSquare(double x)
    {     
        try
        {
            if (x <= 0) throw new IllegalArgumentException();
            else return x * x;
        }
        catch(IllegalArgumentException exc)
        {
            System.out.print("Dlugosc boku kwadratu nie moze byc mniejsza lub rowna ");
            return 0;
        }  
    };
    public double calculateCircle(double x)
    {       
        try
        {
            if (x <= 0) throw new IllegalArgumentException();
            else return Math.PI * x * x;
        }
        catch(IllegalArgumentException exc)
        {
            System.out.print("Promien koła nie może być mniejszy lub rowny ");
            return 0;
        } 
    };
    public double calculateTriangle(double x, double h)
    {
        try
        {
            if (x <= 0 || h <= 0) throw new IllegalArgumentException();
            else return 0.5 * x * h;
        }
        catch(IllegalArgumentException exc)
        {
            System.out.print("Podstawa i wysokosc trojkata nie moga byc mniejsze lub rowne ");
            return 0;
        } 
    };
    public double calculateRectangle(double x, double y)
    {      
        try
        {
            if (x <= 0 || y <= 0) throw new IllegalArgumentException();
            else return x * y;
        }
        catch(IllegalArgumentException exc)
        {
            System.out.print("Dlugosc bokow prostokata nie może być mniejsza lub rowna ");
            return 0;
        }
    };
}
