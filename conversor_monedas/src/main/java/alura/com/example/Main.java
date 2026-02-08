package alura.com.example;

import alura.com.example.modelo.ExchangeRateResponse;
import alura.com.example.servicio.CalculadoraDeDivisas;
import alura.com.example.servicio.ServicioExchangeRate;
import alura.com.example.util.Verificaciones;
import java.util.Scanner;

public class Main {
    private static Scanner lectura = new Scanner(System.in);
    private static ServicioExchangeRate servicio = new ServicioExchangeRate();
    private static CalculadoraDeDivisas calculadora = new CalculadoraDeDivisas();
    private static Verificaciones input = new Verificaciones(lectura);
    public static void main(String[] args) {
        menu();

    }

    private static void menu() {
        int opcion;

        System.out.println("******************************************");
        System.out.println("Sea bienvenido/a al Conversor de Monedas");
                String menu = """
                0) Salir
                1) Dólar estadounidense -> Peso Argentino
                2) Peso Argentino -> Dólar estadounidense
                3) Dólar estadounidense -> Peso mexicano
                4) Peso mexicano -> Dólar estadounidense
                5) Dólar estadounidense -> Peso Colombiano
                6) Peso Colombiano -> Dólar estadounidense
                7) otro
                Elija una opción válida:""";

        do{
            System.out.println(menu);
            opcion = input.leerEntero();
            opcionesDeConversion(opcion);

        } while (opcion != 0);

    }

    private static void opcionesDeConversion (int opcion) {
        switch (opcion) {
            case 0 -> System.out.println("Saliendo......");
            case 1 -> conversion("USD", "ARS");
            case 2 -> conversion("ARS", "USD");
            case 3 -> conversion("USD","MXN");
            case 4 -> conversion("MXN", "USD");
            case 5 -> conversion("USD", "COP");
            case 6 -> conversion("COP", "USD"); 
            case 7 -> menuMonedas();
            default -> System.out.println("Opcion no valida, intentelo de nuevo");
        }
    }

    private static void conversion (String monedaBase, String monedaDestino) {
        System.out.println("Ingresa la cantidad de " + monedaBase 
                            + "que deseas convertir a " + monedaDestino);
        System.out.print(monedaBase + ": ");
        double cantidadBase = input.leerDoble();

        try {
            ExchangeRateResponse datos = servicio.buscarTasas(monedaBase);
            double resultado = calculadora.convertir(cantidadBase, monedaDestino, datos);
            System.out.println("El valor de " + cantidadBase 
                                + " [" + monedaBase + "] corresponde a: " + resultado 
                                + " [" + monedaDestino + "]");
            input.espera();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    public static void menuMonedas() {
        String catalogoMonedas = """
        **********************************************************
        * CATÁLOGO DE MONEDAS DISPONIBLES         *
        **********************************************************
        * CÓDIGO |  PAÍS / MONEDA                               *
        * --------|----------------------------------------------*
        * USD   |  Dólar Estadounidense                        *
        * ARS   |  Peso Argentino                              *
        * BRL   |  Real Brasileño                              *
        * COP   |  Peso Colombiano                             *
        * MXN   |  Peso Mexicano                               *
        * EUR   |  Euro (Unión Europea)                        *
        * CLP   |  Peso Chileno                                *
        * PEN   |  Sol Peruano                                 *
        **********************************************************
        """;
        System.out.println(catalogoMonedas);
        
        System.out.println("ingrese el codigo de la moneda base");
        System.out.print("Codigo: ");
        String monedaBase = input.leerTexto();

        System.out.println("Ingrese el codigo de la moneda destino");
        System.out.print("Codigo: ");
        String monedaDestino = input.leerTexto();

        conversion(monedaBase, monedaDestino);

    }
}