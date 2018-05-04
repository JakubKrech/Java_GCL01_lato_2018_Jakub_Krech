/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kuba
 */
public class BasicCalculatorTest {
    
    BasicCalculator x;
    
    public BasicCalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        System.out.println("Test started...");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        System.out.println("Test finished.");
    }
    
    @Before
    public void setUp()
    {        
        x = new BasicCalculator();
        System.out.println("Testing");
    }
    
    @After
    public void tearDown()
    {
        x = null;
    }


    @Test
    public void testCalculateSum()
    { 
        assertEquals(6, x.calculateSum(4, 2) , 0.1);
        assertEquals(8, x.calculateSum(-10, 18) , 0.1);
        assertEquals(-12, x.calculateSum(-1, -11) , 0.1);
    }

   
    @Test
    public void testCaclulateDifference() 
    {
        assertEquals(2, x.caclulateDifference(4, 2) , 0.1);
        assertEquals(0, x.caclulateDifference(14, 14) , 0.1);
        assertEquals(3, x.caclulateDifference(9, 6) , 0.1);
    }

    @Test
    public void testCalculateMultiplication()
    {
        assertEquals(8, x.calculateMultiplication(4, 2) , 0.1);
        assertEquals(16, x.calculateMultiplication(-4, -4) , 0.1);
        assertEquals(-16, x.calculateMultiplication(4, -4) , 0.1);
    }

 
    @Test
    public void testCalculateDivision() 
    {
        assertEquals(2, x.calculateDivision(4, 2) , 0.1);
        assertEquals(3, x.calculateDivision(9, 3) , 0.1);
        assertEquals(-4, x.calculateDivision(16, -4) , 0.1);
    }

 
    @Test
    public void testCalculatePow() 
    {
        assertEquals(16, x.calculatePow(4, 2) , 0.1);
        assertEquals(16, x.calculatePow(2, 4) , 0.1);
        assertEquals(-8, x.calculatePow(-2, 3) , 0.1);
    }
    
    @Test
    public void testCalculateSqlr() 
    {
        assertEquals(3, x.calculateSqlr(9) , 0.1);
        assertEquals(4, x.calculateSqlr(16) , 0.1);
        assertEquals(5, x.calculateSqlr(25) , 0.1);
    }
}
