//Alamos Navarro Emmanuel Alejandro 
package alura.com.example.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Verificaciones {
    private final Scanner sc;

    private static final int MAX_INTENTOS = 5;
    private static final int MAX_TEXTO    = 30;

    private static final Pattern P_ENTERO    = Pattern.compile("^[+]?[0-9]+$");
    private static final Pattern P_SHORT     = Pattern.compile("^[+]?[0-9]{1,5}$");
    private static final Pattern P_DOUBLE    = Pattern.compile("^[+]?(?:[0-9]+([.,][0-9]+)?)$");
    private static final Pattern P_ANIO = Pattern.compile("^(19[0-9]{2}|20[0-1][0-9]|202[0-5])$");

    public Verificaciones(Scanner scanner) {
        this.sc = scanner;
    }

    public int leerEntero() {
        for (int i = 0; i < MAX_INTENTOS; ) {
            String s = sc.nextLine().trim();
            if (s.isEmpty()) {
                System.out.println("No se puede dejar esta opción en blanco. Introduzca un número entero:");
                continue; 
            }
            if (!P_ENTERO.matcher(s).matches()) {
                System.out.println("Entrada no válida. Debe ser un entero positivo (ej: 0, 1, 23):");
                continue; 
            }
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException ex) {
                System.out.println("El número es demasiado grande para un entero.");
                i++; 
            }
        }
        System.out.println("Se agotaron los intentos");
        return -1;
    }

    public short leerShort() {
        for (int i = 0; i < MAX_INTENTOS; ) {
            String s = sc.nextLine().trim();
            if (s.isEmpty()) {
                System.out.println("No se puede dejar esta opción en blanco. Introduzca un número entero corto:");
                continue;
            }
            if (!P_SHORT.matcher(s).matches()) {
                System.out.println("Entrada no válida. Debe ser un entero positivo (0 a 32767).");
                continue;
            }
            try {
                int val = Integer.parseInt(s);
                if (val < 0 || val > Short.MAX_VALUE) {
                    System.out.println("El valor debe estar entre 0 y " + Short.MAX_VALUE + ".");
                    i++;
                    continue;
                }
                return (short) val;
            } catch (NumberFormatException ex) {
                System.out.println("Número fuera de rango para 'short'.");
                i++;
            }
        }
        System.out.println("Se agotaron los intentos");
        return -1;
    }

    public String leerTexto() {
        boolean saltarVaciaInicial = true; 
        int intentos = 0;

        while (intentos < MAX_INTENTOS) {
            String linea = sc.nextLine();     
            String depurada = linea.trim();  

            if (depurada.isEmpty()) {
                if (saltarVaciaInicial) {
                    saltarVaciaInicial = false;
                    continue; 
                }
                System.out.println("El texto no puede estar vacío. Intenta de nuevo:");
                continue;
            }

            if (depurada.length() > MAX_TEXTO) {
                System.out.println("El texto excede el máximo de " + MAX_TEXTO + " caracteres. Intenta de nuevo:");
                continue;
            }
            return depurada;
        }

        System.out.println("Se agotaron los intentos.");
        return "";
    }

    public Double leerDoble() {
        for (int i = 0; i < MAX_INTENTOS; ) {
            String s = sc.nextLine().trim();

            if (s.isEmpty()) {
                System.out.println("No se puede dejar esta opción en blanco. Introduzca un número decimal:");
                continue;
            }

            if (!P_DOUBLE.matcher(s).matches()) {
                System.out.println("Solo decimales positivos. Ej: 3.23, 2, 22.2 (o con coma).");
                continue;
            }

            s = s.replace(',', '.');
            
            try {
                double val = Double.parseDouble(s);
                if (val < 0) {
                    System.out.println("El valor debe ser positivo.");
                    i++;
                    continue;
                }
                return val;

            } catch (NumberFormatException ex) {
                System.out.println("Número decimal inválido.");
                i++;
            }

        }
        
        System.out.println("Se agotaron los intentos");
        return 0d;
    }

    public boolean leerBooleano() {
        System.out.println("Responder (s/n):");

        for (int i = 0; i < MAX_INTENTOS; ) {
            String s = sc.nextLine().trim().toLowerCase();

            if (s.isEmpty()) {
                System.out.println("No se puede dejar en blanco. Responder (s/n):");
                continue;
            }

            char c = s.charAt(0);
            if (c == 's') return true;
            if (c == 'n') return false;
            System.out.println("Respuesta no válida. Use 's' o 'n':");
            i++;
        }

        System.out.println("Se agotaron los intentos. Se tomará 'no'.");
        return false;
    }

    public short leerAnio() {

        for (int i = 0; i < MAX_INTENTOS; ) {
            String s = sc.nextLine().trim();

            if (s.isEmpty()) {
                System.out.println("No se puede dejar esta opción en blanco. Introduzca un año válido:");
                continue;
            }

            if (!P_ANIO.matcher(s).matches()) {
                System.out.println("Introduzca un año válido (1900 - 2025):");
                continue;
            }

            try {
                return Short.parseShort(s);
            } catch (NumberFormatException ex) {
                System.out.println("Valor fuera de rango para 'short'.");
                i++;
            }

        }
        System.out.println("Se agotaron los intentos");

        return -1;
    }

	public void espera(){
	System.out.println("\nPresione ENTER para continuar...");
		try {
			sc.nextLine();
		} catch (Exception e) {
            
		}
	}
    
    public String textoDeColor(String color, String texto){
        String ansi;

        switch(color.toLowerCase()){
            case "negro" -> ansi = "\u001B[30m";
            case "rojo" -> ansi = "\u001B[31m";
            case "verde" -> ansi = "\u001B[32m";
            case "amarillo" -> ansi = "\u001B[33m";
            case "azul" -> ansi = "\u001B[34m";
            case "magenta" -> ansi = "\u001B[35m";
            case "cyan" -> ansi = "\u001B[36m";
            case "blanco" -> ansi = "\u001B[37m";
            case "gris" -> ansi = "\u001B[30;1m";
            default -> ansi = "\u001B[0m";
        }

        return ansi + texto + "\u001B[0m";
    }
    
    public String getColorTexto(String color){
        String ansi;

        switch(color.toLowerCase()){
            case "negro" -> ansi = "\u001B[30m";
            case "rojo" -> ansi = "\u001B[31m";
            case "verde" -> ansi = "\u001B[32m";
            case "amarillo" -> ansi = "\u001B[33m";
            case "azul" -> ansi = "\u001B[34m";
            case "magenta" -> ansi = "\u001B[35m";
            case "cyan" -> ansi = "\u001B[36m";
            case "blanco" -> ansi = "\u001B[37m";
            case "reset" -> ansi = "\u001B[0m"; 
            default -> ansi = "\u001B[0m";
        }

        return ansi;
    }
}
