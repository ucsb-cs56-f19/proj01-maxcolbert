package earthquakes.searches;

public class EqSearch {

    private int distance;
    private int minmag;
    private double lat;
    private double lon;
    private String location;

    public EqSearch(){
        // distance = 0;
        // minmag = 0;
    }

    public int getDistance() {
        return distance;
    }
    public int getMinmag() {
        return minmag;
    }
    public double getLat() {
        return lat;
    }
    public double getLon() {
        return lon;
    }
    public String getLocation() {
        return location;
    }

    public void setDistance(int newVal) {
        distance = newVal;
    }
    public void setMinmag(int newVal) {
        minmag = newVal;
    }
    public void setLon(double newVal) {
        lon = newVal;
    }
    public void setLat(double newVal) {
        lat = newVal;
    }
    public void setLocation(String newVal) {
        location = newVal;
    }


}