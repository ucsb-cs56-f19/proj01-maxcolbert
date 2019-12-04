package earthquakes.osm;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import earthquakes.osm.Place;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaceCollection {
    private static Logger logger = LoggerFactory.getLogger(PlaceCollection.class);

    public List<Place> places;

    /**
     * Create a Place object from json representation
     * 
     * @param json String of json returned by API endpoint {@code /classes/search}
     * @return a new PlaceCollection object
     * @see <a href=
     *      "https://tools.ietf.org/html/rfc7946">https://tools.ietf.org/html/rfc7946</a>
     */
    public static List<Place> listFromJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Place place = objectMapper.readValue(json, Place.class);
            List<Place> place = objectMapper.readValue(json, new TypeReference<List<Place>>(){});

            return place;
        } catch (JsonProcessingException jpe) {
            logger.error("JsonProcessingException:" + jpe);
            return null;
        } catch (Exception e) {
            logger.error("Exception:" + e);
            return null;
        }

    }
}