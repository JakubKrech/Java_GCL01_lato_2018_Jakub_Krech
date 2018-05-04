public class Zajecia5ObslugaBledow {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BasicCalculator x = new BasicCalculator();
        FieldCalculator y = new FieldCalculator();
        
        // BASIC CALCULATOR METHODS
        System.out.println("BASIC CALCULATOR: ");
        
        System.out.print("Sum: ");
        System.out.println(String.valueOf(x.calculateSum(8, 7)));
        
        System.out.print("Difference: ");
        System.out.println(String.valueOf(x.caclulateDifference(8, 7)));
        
        System.out.print("Multiplication: ");
        System.out.println(String.valueOf(x.calculateMultiplication(8, 7)));
        
        System.out.print("Division: ");
        System.out.println(String.valueOf(x.calculateDivision(10, 4)));
        
        System.out.print("Pow: ");
        System.out.println(String.valueOf(x.calculatePow(4, 3)));
        
        System.out.print("Sqlr: ");
        System.out.println(String.valueOf(x.calculateSqlr(9)) + "\n");
        
        // FIELD CALCULATOR METHODS
        System.out.println("FIELD CALCULATOR: ");
        
        System.out.print("Square: ");
        System.out.println(String.valueOf(y.calculateSquare(8)));
        
        System.out.print("Circle: ");
        System.out.println(String.valueOf(y.calculateCircle(8)));
        
        System.out.print("Triangle: ");
        System.out.println(String.valueOf(y.calculateTriangle(8, 7)));
        
        System.out.print("Rectangle: ");
        System.out.println(String.valueOf(y.calculateRectangle(8, 7)) + "\n");
        
        // ERRORS
        System.out.println("EXCEPTION HANDLING: ");
        
        System.out.print("Division: ");
        System.out.println(String.valueOf(x.calculateDivision(10, 0)));
        
        System.out.print("Sqlr: ");
        System.out.println(String.valueOf(x.calculateSqlr(-10)));
        
        System.out.print("Square: ");
        System.out.println(String.valueOf(y.calculateSquare(0)));
        
        System.out.print("Circle: ");
        System.out.println(String.valueOf(y.calculateCircle(0)));
        
        System.out.print("Triangle: ");
        System.out.println(String.valueOf(y.calculateTriangle(-2, 7)));
        
        System.out.print("Rectangle: ");
        System.out.println(String.valueOf(y.calculateRectangle(-2, 7)) + "\n");
    }
    
}
