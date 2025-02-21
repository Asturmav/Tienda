/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package es.educastur.danielap62.tienda;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alu02d
 */
public class MetodosAuxTest {
    
    public MetodosAuxTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of validarDNI method, of class MetodosAux.
     */
    @Test
    public void testValidarDNI() {
        System.out.println("Test para el metodo validarDni");
    }

    /**
     * Test of calcularLetraDNI method, of class MetodosAux.
     */
    

    /**
     * Test of esInt method, of class MetodosAux.
     */
    @Test
    public void testEsInt() {
        System.out.println("Test para el metodo esInt");
        assertTrue(MetodosAux.esInt("-5"), "El -5 es int");
        assertTrue(MetodosAux.esInt("5"), "El 5 es int");
        assertFalse(MetodosAux.esInt("5.5"), "El 5.5 no es int");
        assertFalse(MetodosAux.esInt("xyt"), "El xyt no es int");
        
        
    }

    /**
     * Test of esDouble method, of class MetodosAux.
     */
    @Test
    public void testEsDouble() {
        System.out.println("Test para el metodo esDouble");
    }
    
}
