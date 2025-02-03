/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package es.educastur.danielap62.tienda;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author alu02d
 */
public class Tienda implements Serializable {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private HashMap <String, Articulo> articulos;
    private HashMap <String, Cliente> clientes;
    
    
    public static void main(String[] args) {
        
    }
    
    //asd
    //<editor-fold defaultstate="collapsed" desc="MENUS">
    public void menu(){
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("");
            System.out.println("BIBLIOTECA\n");
            System.out.println("1 - ARTICULOS");
            System.out.println("2 - CLIENTES");
            System.out.println("3 - PEDIDOS");
            System.out.println("9 - Salir");
            opcion = sc.nextInt();
             
            switch(opcion){
                case 1:{
                    menuArticulos();
                    break;
                }

                case 2:{
                    menuClientes();
                    break;
                }

                case 3:{
                    menuPedidos();
                    break;
                }
            }
        }while(opcion != 9);
    }



    private void menuArticulos() {
      
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("");
            System.out.println("ARTICULOS\n");
            System.out.println("1 - ");
            System.out.println("2 - ");
            System.out.println("9 - Salir");
            opcion = sc.nextInt();
             
            switch(opcion){
                case 1:{
                    break;
                }

                case 2:{
                    break;
                }
                
            }
        }while(opcion != 9);
    }  
    
    


    private void menuClientes() {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("");
            System.out.println("CLIENTES\n");
            System.out.println("1 - ");
            System.out.println("2 - ");
            System.out.println("9 - Salir");
            opcion = sc.nextInt();
             
            switch(opcion){
                case 1:{
                    break;
                }

                case 2:{
                    break;
                }
            }
        }while(opcion != 9);
    }
    
    private void menuPedidos() {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("");
            System.out.println("PEDIDO\n");
            System.out.println("1 - ");
            System.out.println("2 - ");
            System.out.println("9 - Salir");
            opcion = sc.nextInt();
              
            switch(opcion){
                    case 1:{
                        break;
                    }

                    case 2:{
                        break;
                    }
                }
            }while(opcion != 9);
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Gestion pedidos">
    public String generaIdPedido (String idCliente){
        int contador = 0;
        String nuevoId;
        for (Pedido p : pedidos) {
            if (p.getClientePedido().getDni().equalsIgnoreCase(idCliente)){
                contador++;
            }
        }
        contador++;
        nuevoId = idCliente + "-" + String.format("%03d", contador) + "/" + LocalDate.now().getYear() ;
        return nuevoId;
    }
    
    public void stock (int unidadesPed, String id) throws StockAgotado, StockInsuficiente {
        int n=articulos.get(id).getExistencias();
        if(n==0){
            throw new StockAgotado("Stock AGOTADO para el articulo: " + articulos.get(id).getDescripcion());
        }else if(n < unidadesPed){
            throw new StockInsuficiente ("No hay stock suficiente. Me pide" + unidadesPed + "del articulo" 
            + articulos.get(id).getDescripcion() + "y solo se dispone de:" + n);
        }
    }
    
    public boolean validarDNI(String dni) {
        // Verificar que el DNI tiene un formato válido
        if (dni.isBlank() || !dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            return false;
        }
        // Extraer el número y la letra del DNI
        String numeroStr = dni.substring(0, 8);
        char letra = dni.charAt(8);

        // Calcular la letra correspondiente al número del DNI
        char letraCalculada = calcularLetraDNI(Integer.parseInt(numeroStr));
        // Comparar la letra calculada con la letra proporcionada y devolver
        // el resultado de la comparación TRUE/FALSE
        return letra == letraCalculada; 
    }
    public char calcularLetraDNI(int numero) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letras.charAt(numero % 23);
    }
    
    
    public void nuevoPedido() {
        ArrayList<LineaPedido> CestaCompraAux = new ArrayList();
        String dniT, idT, opc, pedidasS;
        int pedidas=0;
        
        sc.nextLine();
        do{
            System.out.println("Cliente Pedido (DNI): ");
            dniT=sc.nextLine().toUpperCase();
            if (dniT.isBlank()) break;
            if (!validarDNI(dniT)) System.out.println("El DNI no es un DNI valido.");
        }while(!clientes.containsKey(dniT));
    
    }
    
    
//</editor-fold>
    
}
