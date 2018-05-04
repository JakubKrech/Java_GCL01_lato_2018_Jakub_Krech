public class BasicCalculator 
{
    
    public double calculateSum(double x, double y)
    {     
        return x + y; 
    };
    public double caclulateDifference(double x, double y)
    {
        return x - y;
    };
    public double calculateMultiplication(double x, double y)
    {
        return x * y;
    };
    public double calculateDivision(double x, double y)
    {
        try
        {
            if (y == 0) throw new IllegalArgumentException();
            else return x / y;
        }
        catch(IllegalArgumentException exc)
        {
            System.out.print("Nie można dzielić przez ");
            return 0;
        }  
    };    
    public double calculatePow(double x, double y)
    {
        return Math.pow(x, y);
    }
    public double calculateSqlr(double x)
    {
        try
        {
            if (x < 0) throw new IllegalArgumentException();
            else return Math.sqrt(x);
        }
        catch(IllegalArgumentException exc)
        {
            System.out.print("Pierwiastkowana wartość nie może być mniejsza od ");
            return 0;
        }       
    };
    
}
