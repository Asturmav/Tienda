/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package es.educastur.danielap62.tienda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class TiendaTest {
    
    private ArrayList<Pedido> pedidos;
    private HashMap <String, Articulo> articulos;
    private HashMap <String, Cliente> clientes;
    
    public TiendaTest() {
        this.pedidos = new ArrayList();
        this.articulos = new HashMap();
        this.clientes = new HashMap();
    }
    Tienda t = new Tienda();
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        t.cargaDatos();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void cargaDatosTest(){
        assertAll(
            () -> assertEquals(5, t.getPedidos().size()),
            () -> assertEquals(8, t.getArticulos().size()),
            () -> assertEquals(5, t.getClientes().size())
        );
        
    
    }
    
    @Test
    public void testTotalPedido() {
        assertAll(
            () -> assertEquals(1500.0, t.totalPedido(t.getPedidos().get(0))),
            () -> assertEquals(285.0, t.totalPedido(t.getPedidos().get(3))),
            () -> assertEquals(390.0, t.totalPedido(t.getPedidos().get(2)))
            
        );
    }
    
    @Test
    public void testTotalCliente() {
        assertAll(
            () -> assertEquals(2000.0, t.totalCliente(t.getClientes().get("90015161S"))),
            () -> assertEquals(0.0, t.totalCliente(t.getClientes().get("82801164N"))),
            () -> assertEquals(390.0, t.totalCliente(t.getClientes().get("96819473F")))
            
        );
    }

    @Test
    public void stockTest(){
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
