package alura.com.example.interfaz;

import alura.com.example.modelo.ExchangeRateResponse;;

public interface ConsultorMoneda {
    ExchangeRateResponse buscarTasas(String monedaBase);
}