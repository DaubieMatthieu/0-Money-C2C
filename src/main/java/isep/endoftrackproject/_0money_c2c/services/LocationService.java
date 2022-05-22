package isep.endoftrackproject._0money_c2c.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import isep.endoftrackproject._0money_c2c.model.Address;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private static final String API_URL = "https://api.geoapify.com/v1/geocode/";
    private static final String API_KEY = "a458fa357b394a6fad39ba6b61cc739b";

    public void completeAddress(Address address) {
        HashMap<String, String> parameters = new HashMap<>() {{
            put("text", address.getText());
            put("format", "json");
            put("apiKey", API_KEY);
        }};
        try {
            HashMap response = call("search", parameters);
            List<HashMap<String, Object>> result = (List<HashMap<String, Object>>) response.get("results");
            HashMap<String, Object> mostPertinentResult = result.get(0);
            address.setLine1((String) mostPertinentResult.get("address_line1"));
            address.setCity((String) mostPertinentResult.get("city"));
            address.setZipCode((String) mostPertinentResult.get("postcode"));
            address.setCountry((String) mostPertinentResult.get("country"));
            address.setLongitude((Double) mostPertinentResult.get("lon"));
            address.setLatitude((Double) mostPertinentResult.get("lat"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HashMap call(String endpoint, Map<String, String> parameters) throws IOException {
        URL url = new URL(API_URL + endpoint + parseParameters(parameters));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        if (con.getResponseCode() > 299) {
            // HTTP request failed
            // TODO fetch error message to display
            return null;
        }
        return new ObjectMapper().readValue(new InputStreamReader(con.getInputStream()), HashMap.class);
    }

    private static String parseParameters(Map<String, String> parameters) {
        return parameters.keySet().stream()
                        .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
                        .collect(Collectors.joining("&", "?", ""));
    }
}
