package earthquakes.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import earthquakes.osm.PlaceCollection;
import earthquakes.osm.Place;
import earthquakes.services.LocationQueryService;
import earthquakes.searches.LocSearch;

import earthquakes.entities.Location;
import earthquakes.repositories.LocationRepository;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.nimbusds.oauth2.sdk.client.ClientReadRequest;

@Controller
public class LocationsController {

    private LocationRepository locationRepository;

    @Autowired
    public LocationsController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;   
    }

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/locations")
    public String index(Model model) {
        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "locations/index";
    }

    @GetMapping("/locations/search")
    public String getLocationsSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            LocSearch locSearch) {
        return "locations/search";
    }

    @GetMapping("/locations/results")
    public String getLocationsResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            LocSearch locSearch) {

        LocationQueryService e = new LocationQueryService();

        model.addAttribute("locSearch", locSearch);
        
        // String json = e.getJSON(locSearch.getLocation());
        // model.addAttribute("json", json);

        String json = e.getJSON(locSearch.getLocation());
        model.addAttribute("json", json);
        List<Place> placeCollection = PlaceCollection.listFromJson(json);
        model.addAttribute("placeCollection",placeCollection);

        // String json = e.getJSON(locSearch.getLocation());
        // model.addAttribute("json", json);
        // FeatureCollection featureCollection = FeatureCollection.fromJSON(json);
        // model.addAttribute("featureCollection",featureCollection);
        return "locations/results";
    }
}