package back.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;

import static back.utils.DateUtils.dateToString;

public class NbpApiService {

    private CacheService<LocalDate, Double> cacheService;

    private static final String NBP_API_EURO_URL = "http://api.nbp.pl/api/exchangerates/rates/a/eur/";
    private static final String KEY_RATES = "rates";
    private static final String KEY_MID_RATE = "mid";

    public NbpApiService() {
        this.cacheService = new CacheService<LocalDate, Double>() {
            @Override
            protected Double cacheReturnValue(final LocalDate key) {
                return parseEuroRate(key);
            }
        };
    }

    public Double getEuroRateByDate(final LocalDate date) {
        return cacheService.getCachedValue(date);
    }

    private Double parseEuroRate(final LocalDate date) {
        final JsonElement json;
        final String stringDate = dateToString(date);

        try {
            json = readJsonFromUrl(NBP_API_EURO_URL + stringDate);
        } catch (final IOException e) {
            //logger
            return null;
        }

        final JsonObject rates = ((JsonObject) json).getAsJsonArray(KEY_RATES).get(0).getAsJsonObject();

        return rates.get(KEY_MID_RATE).getAsDouble();
    }

    private JsonElement readJsonFromUrl(final String url) throws IOException {
        try (InputStream inputStream = new URL(url).openStream()) {
            return new JsonParser().parse(new InputStreamReader(inputStream));
        }
    }
}