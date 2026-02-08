package alura.com.example.servicio;
import alura.com.example.modelo.ExchangeRateResponse;

public class CalculadoraDeDivisas {
    public double convertir(double cantidad, String monedaDestino, ExchangeRateResponse tasas) {
        Double tasa = tasas.tasasDeConversion().get(monedaDestino);
        if (tasa == null) {
            throw new IllegalArgumentException("Moneda no soportada: " + monedaDestino);
        }
        return cantidad * tasa;
    }
}