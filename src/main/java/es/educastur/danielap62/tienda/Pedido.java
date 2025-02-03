/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.educastur.danielap62.tienda;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author alu02d
 */
public class Pedido {
    private String idPedido;
    private Cliente clientePedido;
    private LocalDate fechaPedido;
    private ArrayList <LineaPedido> cestaCompra;

    public Pedido(String idPedido, Cliente clientePedido, LocalDate fechaPedido, ArrayList<LineaPedido> cestaCompra) {
        this.idPedido = idPedido;
        this.clientePedido = clientePedido;
        this.fechaPedido = fechaPedido;
        this.cestaCompra = cestaCompra;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public Cliente getClientePedido() {
        return clientePedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public ArrayList<LineaPedido> getCestaCompra() {
        return cestaCompra;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public void setClientePedido(Cliente clientePedido) {
        this.clientePedido = clientePedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setCestaCompra(ArrayList<LineaPedido> cestaCompra) {
        this.cestaCompra = cestaCompra;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido + ", clientePedido=" + clientePedido + ", fechaPedido=" + fechaPedido + ", cestaCompra=" + cestaCompra + '}';
    }
    
    
}
