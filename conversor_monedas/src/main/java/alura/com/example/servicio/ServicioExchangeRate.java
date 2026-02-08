package alura.com.example.servicio;

import alura.com.example.modelo.ExchangeRateResponse;
import alura.com.example.interfaz.ConsultorMoneda;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicioExchangeRate implements ConsultorMoneda {
    private static final String API_KEY = "539f58db18d1d2cc91511804";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    @Override
    public ExchangeRateResponse buscarTasas(String monedaBase) {
        URI direccion = URI.create(BASE_URL + API_KEY + "/latest/" + monedaBase);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            
            return new Gson().fromJson(response.body(), ExchangeRateResponse.class);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la API: " + e.getMessage());
        }
    }
}