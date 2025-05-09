/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.educastur.danielap62.tienda;

/**
 *
 * @author alu02d
 */
public class MetodosAux {
    public static boolean validarDni(String dni) {
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
    public static char calcularLetraDNI(int numero) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letras.charAt(numero % 23);
    }
    
    /**
     * Metodo para validacion de un Int
     * @param s Recibe como string el numero a comprobar si puede convertirse en un int
     * @return Devuelve true si es posible convertirlo y un false si da un erro
     */
    public static boolean esInt (String s) {
        int n;
        try{
            n=Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }
    
    /**
     * Metodo para validacion de un Double
     * @param s Recibe como string el numero con decimales a comprobar si puede convertirse en un double
     * @return Devuelve true si es posible convertirlo y un false si da un erro
     */
    public static boolean esDouble (String s) {
        double n;
        try{
            n=Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }
}
