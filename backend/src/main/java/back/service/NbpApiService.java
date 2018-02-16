package back.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;

import static back.utils.DateUtils.dateToString;

public class NbpApiService extends AbstractCacheService<Date, Double> {

    private static final String NBP_API_EURO_URL = "http://api.nbp.pl/api/exchangerates/rates/a/eur/";
    private static final String UTF_8_ENCODING = "UTF-8";
    private static final String KEY_RATES = "rates";
    private static final String KEY_MID_RATE = "mid";

    private Double getEuroRateByDate(final Date date) throws IOException {
        final JSONObject json;
        final String stringDate = dateToString(date);

        try {
            json = readJsonFromUrl(NBP_API_EURO_URL + stringDate);
        } catch (final FileNotFoundException e) {
            //logger
            return null;
        }

        final JSONObject rates = (JSONObject) json.getJSONArray(KEY_RATES).get(0);

        return (Double) rates.get(KEY_MID_RATE);
    }

    private JSONObject readJsonFromUrl(final String url) throws IOException, JSONException {
        try (InputStream inputStream = new URL(url).openStream()) {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(UTF_8_ENCODING)));
            final String jsonText = readAll(bufferedReader);

            return new JSONObject(jsonText);
        }
    }

    private String readAll(final Reader rd) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int line;
        while ((line = rd.read()) != -1) {
            stringBuilder.append((char) line);
        }
        return stringBuilder.toString();
    }

    @Override
    protected Double getCacheValue(final Date date) throws IOException {
        return getEuroRateByDate(date);
    }
}