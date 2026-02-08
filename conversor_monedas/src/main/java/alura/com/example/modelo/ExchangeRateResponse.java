package alura.com.example.modelo;
import java.util.Map;

import com.google.gson.annotations.SerializedName;;


public record ExchangeRateResponse (    
    @SerializedName("base_code") String monedaBase,
    @SerializedName("conversion_rates") Map<String, Double> tasasDeConversion,
    String result)
    {

}
