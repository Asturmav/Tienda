/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.educastur.danielap62.tienda;

/**
 *
 * @author alu02d
 */
public class StockAgotado extends Exception{
    public StockAgotado (String cadena){
        super(cadena);
    }
}
