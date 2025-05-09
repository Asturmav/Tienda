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


    @Test
    public void testEsInt() {
        assertAll("esInt",
            () -> assertTrue(MetodosAux.esInt("8")),
            () -> assertTrue(MetodosAux.esInt("46")),
            () -> assertTrue(MetodosAux.esInt("55")),
            () -> assertFalse(MetodosAux.esInt("8.8")),
            () -> assertFalse(MetodosAux.esInt("-55.5")),
            () -> assertFalse(MetodosAux.esInt("HOLA"))
        );
    }



    /**
     * Test of esDouble method, of class MetodosAux.
     */
    @Test
    public void testEsDouble() {
        assertAll("esDouble",
            () -> assertTrue(MetodosAux.esDouble("8")),
            () -> assertTrue(MetodosAux.esDouble("55")),
            () -> assertTrue(MetodosAux.esDouble("8.8")),
            () -> assertTrue(MetodosAux.esDouble("-55.5")),
            () -> assertFalse(MetodosAux.esDouble("HOLA")),
            () -> assertFalse(MetodosAux.esDouble("E"))
        );
    }
    
    @Test
    public void testValidarDni() {
        assertAll("validarDni",
            () -> assertTrue(MetodosAux.validarDni("90015161S"), "DNI válido: 90015161S"),
            () -> assertTrue(MetodosAux.validarDni("90463229C"), "DNI válido: 90463229C"),
            () -> assertFalse(MetodosAux.validarDni("72825528R"), "DNI inválido: 72825528R"),
            () -> assertFalse(MetodosAux.validarDni("90463229X"), "DNI inválido: 90463229X")
        );
    }
    
}
