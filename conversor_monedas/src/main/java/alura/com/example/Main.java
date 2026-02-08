package alura.com.example;

import alura.com.example.modelo.ExchangeRateResponse;
import alura.com.example.servicio.CalculadoraDeDivisas;
import alura.com.example.servicio.ServicioExchangeRate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ServicioExchangeRate servicio = new ServicioExchangeRate();
        CalculadoraDeDivisas calculadora = new CalculadoraDeDivisas();

        System.out.println("******************************************");
        System.out.println("Sea bienvenido/a al Conversor de Monedas");
        
        String menu = """
                1) Dólar -> Peso Argentino
                2) Peso Argentino -> Dólar
                3) Dólar -> Real Brasileño
                4) Real Brasileño -> Dólar
                5) Dólar -> Peso Colombiano
                6) Peso Colombiano -> Dólar
                7) Salir
                Elija una opción válida:""";

        while (true) {
            System.out.println(menu);
            var opcion = lectura.nextInt();

            if (opcion == 7) break;

            try {
                ExchangeRateResponse datos = servicio.buscarTasas("USD");
                double resultado = calculadora.convertir(100, "ARS", datos);
                System.out.println("El valor de 100 [USD] corresponde a: " + resultado + " [ARS]");
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Programa finalizado. ¡Gracias!");

    }
}