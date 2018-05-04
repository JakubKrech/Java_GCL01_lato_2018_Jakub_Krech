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
public class FieldCalculatorTest {
    
    FieldCalculator x;
    
    public FieldCalculatorTest() {
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
        x = new FieldCalculator();
        System.out.println("Testing");
    }
    
    @After
    public void tearDown()
    {
        x = null;
    }

    @Test
    public void testCalculateSquare() 
    {
        assertEquals(25, x.calculateSquare(5) , 0.1);
        assertEquals(16, x.calculateSquare(4) , 0.1);
        assertEquals(9, x.calculateSquare(3) , 0.1);
    }

    @Test
    public void testCalculateCircle()
    {
        assertEquals(78.5, x.calculateCircle(5) , 0.1);
        assertEquals(50.36, x.calculateCircle(4) , 0.1);
        assertEquals(28.3275, x.calculateCircle(3) , 0.1);
    }

    @Test
    public void testCalculateTriangle()
    {
        assertEquals(20, x.calculateTriangle(5, 8) , 0.1);
        assertEquals(6, x.calculateTriangle(3, 4) , 0.1);
        assertEquals(60, x.calculateTriangle(12, 10) , 0.1);
    }

    @Test
    public void testCalculateRectangle() 
    {
        assertEquals(24, x.calculateRectangle(4, 6) , 0.1);
        assertEquals(50, x.calculateRectangle(5, 10) , 0.1);
        assertEquals(3, x.calculateRectangle(2, 1.5) , 0.1);
    }
    
}
