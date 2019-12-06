package earthquakes.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long placeId;

    private String location;

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Long getPlaceId() { return placeId; }
    public void setPlaceId(Long placeId) { this.placeId = placeId; }

    // public String getLogin() { return login; }
    // public void setLogin(String login) { this.login = login; }
    // public String getUid() { return uid; }
    // public void setUid (String uid) { this.uid = uid; }
}
