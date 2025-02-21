/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.educastur.danielap62.tienda;

import java.io.Serializable;

/**
 *
 * @author alu02d
 */
public class LineaPedido implements Serializable {
    private String idArticulo;
    private int unidades;

    public LineaPedido(String idArticulo, int unidades) {
        this.idArticulo = idArticulo;
        this.unidades = unidades;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        return "{" + "-" + idArticulo + ", -" + unidades + '}';
    }
    
}
