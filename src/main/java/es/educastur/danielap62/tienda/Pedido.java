/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.educastur.danielap62.tienda;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author alu02d
 */
public class Pedido implements Serializable, Comparable <Pedido>{
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
        return "{" + "-" + idPedido + ", -" + clientePedido + ", -" + fechaPedido + ", -" + cestaCompra + '}';
    }
    
    @Override
    public int compareTo(Pedido p){
        //return this.fechaPedido.compareTo(p.getfechaPedido());
        return this.idPedido.compareTo(p.getIdPedido());
    }
    
}
