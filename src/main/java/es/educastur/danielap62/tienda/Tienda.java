/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package es.educastur.danielap62.tienda;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public HashMap<String, Articulo> getArticulos() {
        return articulos;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }
    
    
    public Tienda(){
        this.pedidos = new ArrayList();
        this.articulos = new HashMap();
        this.clientes = new HashMap();
    }
    
    public static void main(String[] args) {
        Tienda t= new Tienda();
        t.cargaDatos();
        //t.leerArchivos();
        t.menu();
        //t.backup();
    }
    

    //<editor-fold defaultstate="collapsed" desc="MENUS">
    public void menu(){
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("");
            System.out.println("TIENDA\n");
            System.out.println("1 - ARTICULOS");
            System.out.println("2 - CLIENTES");
            System.out.println("3 - PEDIDOS");
            System.out.println("4 - BACKUP CLIENTES");
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
                
                case 4:{
                    clientesTxtBackup();
                    clientesTxtLeer();
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
            System.out.println("2 - LISTA ARTICULOS");
            System.out.println("3 - LISTA ARTICULOS POR VENDIDOS");
            System.out.println("4 - LISTA ARTICULOS SECCION");
            System.out.println("5 - BACKUP POR SECCION");
            System.out.println("9 - Salir");
            opcion = sc.nextInt();
             
            switch(opcion){
                case 1:{
                    
                    break;
                }

                case 2:{
                    listaArticulos();
                    break;
                }
                
                case 3:{
                    listaArticulosPorVendidos();
                    break;
                }
                
                case 4:{
                    articuloSeccion();
                    break;
                }
                case 5:{
                    backupSeccion();
                    leerarchivoSeccion();
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
            System.out.println("1 - NUEVO CLIENTE");
            System.out.println("2 - LISTA DE CLIENTES");
            System.out.println("3 - MODIFICAR CLIENTE");
            System.out.println("4 - BORRAR CLIENTE");
            System.out.println("9 - Salir");
            opcion = sc.nextInt();
             
            switch(opcion){
                case 1:{
                    
                    break;
                }

                case 2:{
                    listaClientes();
                    break;
                }
                
                case 3:{
                    
                    break;
                }
                
                case 4:{
                    
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
            System.out.println("PEDIDOS\n");
            System.out.println("1 - NUEVO PEDIDO");
            System.out.println("2 - LISTA PEDIDOS ");
            System.out.println("3 - LISTA PEDIDOS POR TOTAL ");
            System.out.println("9 - Salir");
            opcion = sc.nextInt();
              
            switch(opcion){
                    case 1:{
                        nuevoPedido();
                        break;
                    }

                    case 2:{
                        listaPedidos();
                        break;
                    }
                    
                    case 3:{
                        listarPedidosPorTotal();
                        break;
                    }
                }
            }while(opcion != 9);
    }
//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Gestion Articulos">
    public void listaArticulos(){
        ArrayList <Articulo> articulosAux = new ArrayList(articulos.values());
        System.out.println("Comparando Articulos por ID");
        Collections.sort(articulosAux);
        
        for (Articulo a : articulosAux) {
            System.out.println(a);
        }
        
        System.out.println("\nComparandolos por Precio:");
        Collections.sort(articulosAux,new ComparaArticulosPorPrecio());
        
        for (Articulo a : articulosAux) {
            System.out.println(a);
        }
        System.out.println("\nComparandolos por Existencias:");
        Collections.sort(articulosAux, new ComparaArticulosPorExistencias());
        for (Articulo a : articulosAux) {
            System.out.println(a);
        }
    }
    
    
    public int unidadesArticuloVendidas(Articulo a){
      int total=0;
        for (Pedido p : pedidos) {
            for(LineaPedido l:p.getCestaCompra()){
                if(l.getIdArticulo().equals(a.getIdArticulo())){
                    total+=l.getUnidades();    
                }
                
            }
        }
        return total;
    }
    
    public void listaArticulosPorVendidos(){
        articulos.values().stream().sorted(Comparator.comparing(a->unidadesArticuloVendidas((Articulo) a)).reversed())
                .forEach(a->System.out.println(a.getDescripcion() +"\t Unidades Vendidas: " + unidadesArticuloVendidas(a) ));
                
                

    }
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Gestion Clientes">
    public void nuevoCliente(){
        String dni, nombre, telefono, email;
        
        System.out.println("Cual es el dni del Cliente?");
        dni = sc.next();
        
        System.out.println("Cual es el nombre del Cliente?");
        nombre = sc.next();
        
        System.out.println("Cual es el telefono del Cliente?");
        telefono = sc.next();
        
        System.out.println("Cual es el email del Cliente?");
        email = sc.next();
        
        clientes.put(dni, new Cliente(dni, nombre, telefono, email));
    }
    
    public void listaClientes(){
        
        clientes.values().stream().sorted(Comparator.comparing(c->totalCliente((Cliente) c)).reversed()).forEach(p-> System.out.println(p+"\t - IMPORTE TOTAL: " + totalCliente(p)));

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
    
    public void stock(String id, int unidadesPed) throws StockAgotado, StockInsuficiente {
        int n=articulos.get(id).getExistencias();
        if (n==0){
            throw new StockAgotado ("Stock AGOTADO para el articulo: "+ articulos.get(id).getDescripcion());
        }else if (n < unidadesPed){
            throw new StockInsuficiente ("No hay Stock suficiente. Me pide  " + unidadesPed + " de "
                                        + articulos.get(id).getDescripcion()
                                        + " y sólo se dispone de: "+ n);
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
    
    

    
    
  
    @SuppressWarnings("empty-statement")
    public void nuevoPedido() {
        //ARRAYLIST AUXILIAR PARA CREAR EL PEDIDO
        ArrayList<LineaPedido> CestaCompraAux = new ArrayList();
        String dniT, idT, opc, pedidasS;
        int pedidas=0;
        sc.nextLine();
        do{
            System.out.println("CLIENTE PEDIDO (DNI):");
            dniT=sc.nextLine().toUpperCase();
            //EN CUALQUIER MOMENTO PODEMOS SALIR DEL BUCLE TECLEANDO RETORNO
            if (dniT.isBlank()) break;
            if (!MetodosAux.validarDni(dniT)|| !clientes.containsKey(dniT)) System.out.println("El DNI no es válido O NO ES CLIENTE DE LA TIENDA");;
        }while (!clientes.containsKey(dniT));
        
        if (!dniT.isBlank()){
            System.out.println("\t\tCOMENZAMOS CON EL PEDIDO");
            System.out.println("INTRODUCE CODIGO ARTICULO (RETURN PARA TERMINAR): ");
            idT=sc.nextLine();
                 
            while (!idT.isEmpty()) {
                if (!articulos.containsKey(idT)){
                    System.out.println("El ID articulo tecleado no existe");
                }else{
                    System.out.print("(" + articulos.get(idT).getDescripcion()+ ") - UNIDADES? ");
                    do {
                        pedidasS=sc.nextLine();
                    }while(!MetodosAux.esInt(pedidasS)); 

                    pedidas=Integer.parseInt(pedidasS);

                    try{
                        stock(idT,pedidas); // LLAMO AL METODO STOCK, PUEDEN SALTAR 2 EXCEPCIONES
                        CestaCompraAux.add(new LineaPedido(idT,pedidas));
                        articulos.get(idT).setExistencias(articulos.get(idT).getExistencias()-pedidas);
                    }catch (StockAgotado e){
                        System.out.println(e.getMessage());
                    }catch (StockInsuficiente e){
                        System.out.println(e.getMessage());
                        int disponibles=articulos.get(idT).getExistencias();
                        System.out.print("QUIERES LAS " + disponibles + " UNIDADES DISPONIBLES? (S/N) ");
                        opc=sc.next();
                        if (opc.equalsIgnoreCase("S")){
                            CestaCompraAux.add(new LineaPedido(idT,disponibles));
                            articulos.get(idT).setExistencias(articulos.get(idT).getExistencias()-disponibles);
                        }
                    }
                }
                System.out.println("INTRODUCE CODIGO ARTICULO (RETURN PARA TERMINAR): ");
                idT=sc.nextLine();
            }
         
            //IMPRIMO EL PEDIDO Y SOLICITO ACEPTACION DEFINITIVA DEL MISMO 
            for (LineaPedido l:CestaCompraAux)
            {
                System.out.println(articulos.get(l.getIdArticulo()).getDescripcion() + " - ("+ l.getUnidades() + ")");
            }
            System.out.println("ESTE ES TU PEDIDO. PROCEDEMOS? (S/N)   ");
            opc=sc.next();
            if (opc.equalsIgnoreCase("S")){
            // ESCRIBO EL PEDIDO DEFINITIVAMENTE Y DESCUENTO LAS EXISTENCIAS PEDIDAS DE CADA ARTICULO
                LocalDate hoy=LocalDate.now();
                pedidos.add(new Pedido(generaIdPedido(dniT),clientes.get(dniT),hoy,CestaCompraAux));
            }
            else{    
                for (LineaPedido l:CestaCompraAux)
                {
                    articulos.get(l.getIdArticulo()).setExistencias(articulos.get(l.getIdArticulo()).getExistencias()+l.getUnidades());
                } 
            }
        }
    }
    
    
    public void listaPedidos(){
        Collections.sort(pedidos);
        
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
        
        Collections.reverse(pedidos);
        
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }


    /*
        Tipos de ordenacion:
        1: sorted()
        2: sorted(new ComparaPedidosPorTotal())
        3: sorted(Comparator.comparing(p->totalPedido(p)))
        */
    //Si queremos pasar un HashMap a stream tenemos que hacerlo: articulos.values().stream()
    public void listarPedidosPorTotal(){
        pedidos.stream().sorted(Comparator.comparing(p->totalPedido((Pedido) p)).reversed())
                .forEach(p-> System.out.println(p+"\t - IMPORTE TOTAL: " + totalPedido(p)));
        /*El system.out::println es una forma resumida de decirle que imprima cada cosa, es equivalente a:
        pedidos.stream().forEach(p->System.out.println(p));
        */
        
        //Con user pedido por teclado
        System.out.println("Teclea NOMBRE CLIENTE");
        String nombre=sc.next().toUpperCase();
        pedidos.stream().filter(p->p.getClientePedido().getNombre().equals(nombre)).sorted(Comparator.comparing(p->totalPedido((Pedido) p)).reversed()).filter(p->totalPedido(p)>500)
                .forEach(p-> System.out.println(p+"\t - IMPORTE TOTAL: " + totalPedido(p)));
        
        System.out.println("Teclea SECCION");
        char s=sc.next().charAt(0);
        articulos.values().stream().filter(a->a.getIdArticulo().charAt(0)==s).sorted(new ComparaArticulosPorPrecio()).forEach(System.out::println);

    }
    
    public double totalPedido(Pedido p){
        /*
        VERSION "CLASICA"
        double total=0;
        for(LineaPedido l:p.getCestaCompra()){
            total+=(articulos.get(l.getIdArticulo()).getPvp())*l.getUnidades();
            //Conseguimos el precio y lo multiplicamos por las unidades que compro
        }
        return total;
    */
        return p.getCestaCompra().stream()
                .mapToDouble(l->articulos.get(l.getIdArticulo()).getPvp()
                *l.getUnidades()).sum();
    }
    
    public double totalCliente(Cliente c){
        double totalC=0;


            for (int i = 0; i < pedidos.size(); i++) {
                if(pedidos.get(i).getClientePedido().equals(c)){
                    totalC+=totalPedido(pedidos.get(i));
                }
            }
           
        return totalC;
    }
    
    public double totalCliente2(Cliente c){
        return pedidos.stream().filter(p->p.getClientePedido().equals(c))
                .mapToDouble(p->p.getCestaCompra().stream()
                .mapToDouble(lp->lp.getUnidades()*articulos.get(lp.getIdArticulo())
                        .getPvp()).sum()).sum();
    }
    
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Persistencia">
    public void backup(){
        
        try (ObjectOutputStream oosArticulos = new ObjectOutputStream(new FileOutputStream("articulos.dat"));
            ObjectOutputStream oosClientes = new ObjectOutputStream(new FileOutputStream("clientes.dat"));
            ObjectOutputStream oosPedidos = new ObjectOutputStream (new FileOutputStream("pedidos.dat"))) {
	   	   
            for (Articulo a : articulos.values()) {
                oosArticulos.writeObject(a);
            }
            for (Cliente c:clientes.values()) {
                oosClientes.writeObject(c);
            }
            for (Pedido p:pedidos){
                 oosPedidos.writeObject(p);
            }
            System.out.println("Copia de seguridad realizada con éxito.");
	    
            /*Como estamos creando el objeto en caso de no encontrarlo lo que hara sera crearlo asi que
            esta excepcion seria posible obviarla 
            */
        } catch (FileNotFoundException e) {
                 System.out.println("Archivo no encontrado");                                                          
        } catch (IOException e) {
                 System.out.println(e.toString());
        } 
    }
    
    public void leerArchivos(){
        try (ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream("articulos.dat"))){
            Articulo a;
            while ( (a=(Articulo)oisArticulos.readObject()) != null){
                 articulos.put(a.getIdArticulo(), a);
            } 
	} catch (FileNotFoundException e) {
                 System.out.println(e.toString());    
        } catch (EOFException e){
            
        } catch (ClassNotFoundException | IOException e) {
                System.out.println(e.toString()); 
        } 
        
        try (ObjectInputStream oisClientes = new ObjectInputStream(new FileInputStream("clientes.dat"))){
            Cliente c;
            while ( (c=(Cliente)oisClientes.readObject()) != null){
                 clientes.put(c.getDni(), c);
            } 
	} catch (FileNotFoundException e) {
                 System.out.println(e.toString());    
        } catch (EOFException e){
            
        } catch (ClassNotFoundException | IOException e) {
                System.out.println(e.toString()); 
        }
        
        
        try (ObjectInputStream oisPedidos = new ObjectInputStream(new FileInputStream("pedidos.dat"))){
            Pedido p;
            while ( (p=(Pedido)oisPedidos.readObject()) != null){
                 pedidos.add(p);
            } 
	} catch (FileNotFoundException e) {
                 System.out.println(e.toString());    
        } catch (EOFException e){
            
        } catch (ClassNotFoundException | IOException e) {
                System.out.println(e.toString()); 
        }
    }
    
    public void clientesTxtBackup(){
        try(BufferedWriter bfwClientes = new BufferedWriter(new FileWriter("clientes.csv"))){
            for(Cliente c : clientes.values()){
                bfwClientes.write(c.getDni() + ", " + c.getNombre() + ", " + c.getTelefono() + ", " + c.getEmail() + "\n");
            }
            
            System.out.println("Clientes guardados con exito");
        }catch (FileNotFoundException e) {
                 System.out.println("Archivo no encontrado");                                                          
        } catch (IOException e) {
                 System.out.println(e.toString());
        } 
    }
    
    public void clientesTxtLeer(){
        HashMap <String, Cliente> clientesAux = new HashMap();
        try(Scanner scClientes = new Scanner(new File ("clientes.csv"))){
            while(scClientes.hasNextLine()){
                String [] atributos = scClientes.nextLine().split("[,]");
                Cliente c=new Cliente(atributos[0], atributos[1], atributos[2],atributos[3]);
                clientesAux.put(atributos[0], c);
            }
        }catch (IOException e) {
                 System.out.println(e.toString());
        }
        clientesAux.values().forEach(System.out::println);
    }
//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Ejercicios clase">
    
    public void articuloSeccion(){
        System.out.println("Que seccion quieres? (5 Para todos)");
        String seccion = sc.next();
        ArrayList<Articulo> articulosAux = new ArrayList();
        try (ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream("articulos.dat"))){
            Articulo a;
            while ( (a=(Articulo)oisArticulos.readObject()) != null){
                if(seccion.equals("5")){
                    articulosAux.add(a);
                } 
                else if(a.getIdArticulo().startsWith(seccion)){
                     articulosAux.add(a);
                 }
            } 
	} catch (FileNotFoundException e) {
                 System.out.println(e.toString());    
        } catch (EOFException e){
            
        } catch (ClassNotFoundException | IOException e) {
                System.out.println(e.toString()); 
        }
        
        articulosAux.forEach(System.out::println);
    }
    
    
    
    public void backupSeccion(){
        
        try (ObjectOutputStream oosSeccion1 = new ObjectOutputStream(new FileOutputStream("seccion1.dat"));
            ObjectOutputStream oosSeccion2 = new ObjectOutputStream(new FileOutputStream("seccion2.dat"));
            ObjectOutputStream oosSeccion3 = new ObjectOutputStream(new FileOutputStream("seccion3.dat"));
            ObjectOutputStream oosSeccion4 = new ObjectOutputStream (new FileOutputStream("seccion4.dat"))) {
	   	   
            for (Articulo a : articulos.values()) {
                if(a.getIdArticulo().startsWith("1")){
                    oosSeccion1.writeObject(a);
                }
                else if(a.getIdArticulo().startsWith("2")){
                    oosSeccion2.writeObject(a);
                }
                else if(a.getIdArticulo().startsWith("3")){
                    oosSeccion3.writeObject(a);
                }
                else if(a.getIdArticulo().startsWith("4")){
                    oosSeccion4.writeObject(a);
                }
            }
            
            System.out.println("Copia de seguridad por secciones realizada con exito.");
	    
        } catch (FileNotFoundException e) {
                 System.out.println(e.toString());                                                          
        } catch (IOException e) {
                 System.out.println(e.toString());
        } 
    }
    
    public void leerarchivoSeccion(){
        System.out.println("Teclea la seccion cuyo archivo quieras comprobar");
        String seccion = sc.next();
        String nombreArchivo=null;
        switch(seccion.charAt(0)){
            case '1':
                nombreArchivo="seccion1.dat";
                break;
            case '2':
                nombreArchivo="seccion2.dat";
                break;
            case '3':
                nombreArchivo="seccion3.dat";
                break;
            case '4':
                nombreArchivo="seccion4.dat";
                break;
        }
        
        ArrayList<Articulo> articulosAux = new ArrayList();
        try (ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream(nombreArchivo))){
            Articulo a;
            while ( (a=(Articulo)oisArticulos.readObject()) != null){
                 articulosAux.add(a);
            } 
	} catch (FileNotFoundException e) {
                 System.out.println(e.toString());    
        } catch (EOFException e){
            
        } catch (ClassNotFoundException | IOException e) {
                System.out.println(e.toString()); 
        } 
        
        articulosAux.forEach(System.out::println);
    }
    
    public void backupPedidosClientes() {
       
        /*OPCION 1 - CLIENTE A CLIENTE - IMPLICA PROCESAR CADA CLIENTE POR SEPARADO Y RECORRER PEDIDOS 
        TANTAS VECES COMO CLIENTES HAY */
        boolean tienePedidos;
        String archivo;
        for (Cliente c:clientes.values()){
            tienePedidos=false;       
            for (Pedido p: pedidos ){
                if(p.getClientePedido().equals(c)){
                    tienePedidos=true;
                    break;
                }
            }
            if (tienePedidos){
                archivo="PedidosCliente_" + c.getNombre()+".dat";
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo)))
                {
                   for (Pedido p: pedidos ){
                        if(p.getClientePedido().equals(c)) {
                            oos.writeObject(p);
                        }
                   }
                } catch (IOException e) {
                   System.out.println(e.toString());
                } 
                
            }
        }
        System.out.println("ARCHIVOS CREADOS CORRECTAMENTE\n");
        
        /*AHORA SOLICITAMOS EL DNI DE UN CLIENTE PARA MOSTRAR SUS PEDIDOS
        DESDE EL ARCHIVO .dat CORRESPONDIENTE*/
         
        String dniT; 
        //NO PERMITIMOS ENTRADA DE DNIs NO VÁLIDOS O QUE NO ESTÁN EN LA TIENDA
        do{
            System.out.println("DNI CLIENTE:");
            dniT=sc.next().toUpperCase();    
        }while (!clientes.containsKey(dniT)||!MetodosAux.validarDni(dniT));
        
        //COMPROBAMOS AHORA SI EL DNI TIENE PEDIDOS.
        //SI NO LOS TIENE NO SE CREÓ SU ARCHIVO
        tienePedidos=false;       
        for (Pedido p: pedidos ){
            if(p.getClientePedido().equals(clientes.get(dniT))) {
                tienePedidos=true;
                break;
            }
        }
        
        if (tienePedidos){
            archivo="PedidosCliente_" + clientes.get(dniT).getNombre()+".dat";
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo)))
            {
                Pedido p;
                while ( (p=(Pedido)ois.readObject()) != null){
                     System.out.println("\nPEDIDO: " + p.getIdPedido() + " DE: " + p.getClientePedido().getNombre());
                     for (LineaPedido l:p.getCestaCompra()){
                         System.out.println(articulos.get(l.getIdArticulo()).getDescripcion()
                                 + "\t Unidades: " +l.getUnidades());
                     }
                } 
            } catch (EOFException e) {
                System.out.println("Fin archivo");
            } catch (IOException e) {
                System.out.println("No existen pedidos para ese DNI");
            } catch (ClassNotFoundException ex) {
            }
        } 
         
        /* BACKUP OPCION 2 - VAMOS PEDIDO A PEDIDO Y SOBRE LA MARCHA ABRIENDO 1 ObjectOutputStream POR CLIENTE
        Y PASANDO SUS PEDIDOS AL CORRESPONDIENTE ARCHIVO - LO HACEMOS TODO DE UNA PASADA PERO HAY QUE ABRIR
        A LA VEZ BASTANTES CANALES DE E/S Y MANEJAR UN HASHMAP DE CLIENTES --> ObjectOutputStream */ 
        /*
        HashMap <String,ObjectOutputStream> clientesConPedido =new HashMap();
        String archivo;
        String nombreCliente;
        for (Pedido p:pedidos){
            nombreCliente=p.getClientePedido().getNombre();
            try{
                if (!clientesConPedido.containsKey(nombreCliente)){
                    archivo= "PedidosCliente_" + nombreCliente +".dat";
                    clientesConPedido.put(nombreCliente, new ObjectOutputStream(new FileOutputStream(archivo)));
                    clientesConPedido.get(nombreCliente).writeObject(p);
                }else{
                    clientesConPedido.get(nombreCliente).writeObject(p);
                }
            }
            catch (IOException e) {
                System.out.println(e.toString());
            } 
        }
        //Cerramos todos los canales de serialización hacía los archivos pues no hemso podido hacer Try_with_resources
        for (ObjectOutputStream oos: clientesConPedido.values()){
            try {
                oos.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        
        String dniT; 
        do{
            System.out.println("DNI CLIENTE:");
            dniT=sc.next().toUpperCase();    
        }while (!clientes.containsKey(dniT)||!MetodosAux.validarDNI(dniT));
        
              
        if (clientesConPedido.containsKey(clientes.get(dniT).getNombre())){
            archivo="PedidosCliente_" + clientes.get(dniT).getNombre()+".dat";
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo)))
            {
                Pedido p=null;
                while ( (p=(Pedido)ois.readObject()) != null){
                     System.out.println("\nPEDIDO: " + p.getIdPedido() + " DE: " + p.getClientePedido().getNombre());
                     for (LineaPedido l:p.getCestaCompra()){
                         System.out.println(articulos.get(l.getIdArticulo()).getDescripcion()
                                 + "\t Unidades: " +l.getUnidades());
                     }
                } 
            } catch (EOFException e) {
                System.out.println("Fin archivo");
            } catch (IOException e) {
                System.out.println(e.toString());
            } catch (ClassNotFoundException e) {
                System.out.println(e.toString());
            }
        }*/
        
    }

//</editor-fold>
    
    /*
    public void cargaDatos(){
        
       clientes.put("80580845T",new Cliente("80580845T","ANA","658111111","ana@gmail.com"));
       clientes.put("36347775R",new Cliente("36347775R","LOLA","649222222","lola@gmail.com"));
       clientes.put("63921307Y",new Cliente("63921307Y","JUAN","652333333","juan@gmail.com"));
       clientes.put("02337565Y",new Cliente("02337565Y","EDU","634567890","edu@gmail.com"));
              
       articulos.put("1-11",new Articulo("1-11","RATON LOGITECH ST ",14,15));
       articulos.put("1-22",new Articulo("1-22","TECLADO STANDARD  ",9,18));
       articulos.put("2-11",new Articulo("2-11","HDD SEAGATE 1 TB  ",16,80));
       articulos.put("2-22",new Articulo("2-22","SSD KINGSTOM 256GB",9,70));
       articulos.put("2-33",new Articulo("2-33","SSD KINGSTOM 512GB",0,200));
       articulos.put("3-22",new Articulo("3-22","EPSON PRINT XP300 ",5,80));
       articulos.put("4-11",new Articulo("4-11","ASUS  MONITOR  22 ",5,100));
       articulos.put("4-22",new Articulo("4-22","HP MONITOR LED 28 ",5,180));
       articulos.put("4-33",new Articulo("4-33","SAMSUNG ODISSEY G5",12,580));
       
       LocalDate hoy = LocalDate.now();
       pedidos.add(new Pedido("80580845T-001/2024",clientes.get("80580845T"),hoy.minusDays(1), new ArrayList<>
        (List.of(new LineaPedido("1-11",3),new LineaPedido("4-22",3)))));                                                                                                                                                               
       pedidos.add(new Pedido("80580845T-002/2024",clientes.get("80580845T"),hoy.minusDays(2), new ArrayList<>
        (List.of(new LineaPedido("4-11",3),new LineaPedido("4-22",2),new LineaPedido("4-33",4)))));
       pedidos.add(new Pedido("36347775R-001/2024",clientes.get("36347775R"),hoy.minusDays(3), new ArrayList<>
        (List.of(new LineaPedido("4-22",1),new LineaPedido("2-22",3)))));
       pedidos.add(new Pedido("36347775R-002/2024",clientes.get("36347775R"),hoy.minusDays(5), new ArrayList<>
        (List.of(new LineaPedido("4-33",3),new LineaPedido("2-11",3)))));
       pedidos.add(new Pedido("63921307Y-001/2024",clientes.get("63921307Y"),hoy.minusDays(4), new ArrayList<>
        (List.of(new LineaPedido("2-11",5),new LineaPedido("2-33",3),new LineaPedido("4-33",2)))));
    }
*/
      public void cargaDatos(){
       clientes.put("90015161S",new Cliente("90015161S","ANA ","658111111","ana@gmail.com"));
       clientes.put("96819473F",new Cliente("96819473F","ANTONIO","649222222","antonio@gmail.com"));
       clientes.put("95767515T",new Cliente("95767515T","AURORA","652333333","aurora@gmail.com"));
       clientes.put("97801164N",new Cliente("97801164N","EMILIO","649222222","emilio@gmail.com"));
       clientes.put("82801164N",new Cliente("82801164N","EVA","652333333","eva@gmail.com"));
         
       
       articulos.put("1-11",new Articulo("1-11","RATON LOGITECH ST ",14,15));
       articulos.put("1-22",new Articulo("1-22","TECLADO STANDARD  ",9,18));
       articulos.put("2-11",new Articulo("2-11","HDD SEAGATE 1 TB  ",16,80));
       articulos.put("2-22",new Articulo("2-22","SSD KINGSTOM 256GB",0,70));
       articulos.put("2-33",new Articulo("2-33","SSD KINGSTOM 512GB",5,200));
       articulos.put("3-22",new Articulo("3-22","EPSON PRINT XP300 ",5,80));
       articulos.put("4-11",new Articulo("4-11","ASUS  MONITOR  22 ",10,100));
       articulos.put("4-22",new Articulo("4-22","HP MONITOR LED 28 ",5,180));
      
       LocalDate hoy = LocalDate.now();
       pedidos.add(new Pedido("90015161S-001/2025",clientes.get("90015161S"),hoy.minusDays(1), new ArrayList<>
        (List.of(new LineaPedido("2-33",5),new LineaPedido("4-11",5)))));                                                                                                                                                               
       pedidos.add(new Pedido("90015161S-002/2025",clientes.get("90015161S"),hoy.minusDays(2), new ArrayList<>
        (List.of(new LineaPedido("2-11",5),new LineaPedido("4-11",1)))));
       pedidos.add(new Pedido("96819473F-001/2025",clientes.get("96819473F"),hoy.minusDays(3), new ArrayList<>
        (List.of(new LineaPedido("4-22",1),new LineaPedido("2-22",3)))));
       pedidos.add(new Pedido("95767515T-001/2025",clientes.get("95767515T"),hoy.minusDays(5), new ArrayList<>
        (List.of(new LineaPedido("1-11",3),new LineaPedido("2-11",3)))));
       pedidos.add(new Pedido("97801164N-001/2025",clientes.get("97801164N"),hoy.minusDays(4), new ArrayList<>
        (List.of(new LineaPedido("2-11",1),new LineaPedido("2-33",3),new LineaPedido("1-11",2)))));
    } 
}
