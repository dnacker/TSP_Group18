import java.util.LinkedList;
import java.util.List;

public class CityNode {
    private City city;
//    private CityNode closestCity;
    private List<CityNode> connectedCities;

    public CityNode(City c) {
        city = c;
        connectedCities = new LinkedList<>();
    }

    public void connectCity(CityNode other) {
        connectedCities.add(other);
    }

    public City getCity() {
        return city;
    }

    public List<CityNode> getNeighbors() {
        return connectedCities;
    }

    public String toString() {
        String connected = "(";
        for (int i = 0; i < connectedCities.size(); i++) {
            connected += connectedCities.get(i).getCity();
            if (i != connectedCities.size() - 1){
                 connected += ", ";
            }
        }
        connected += ")";
        return city.getName() + " connected to: " + connected;
    }
}
