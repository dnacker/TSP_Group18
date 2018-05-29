import java.util.ArrayList;
import java.util.List;

public class CityGraph {
    private List<CityNode> cityNodes;

    public CityGraph() {
        cityNodes = new ArrayList<>();
    }

    public CityGraph(List<City> cities) {
        cityNodes = new ArrayList<>();
        for (City c: cities) {
            cityNodes.add(new CityNode(c));
        }
    }

    public void addCityNode(CityNode cityNode) {
        cityNodes.add(cityNode);
    }

    public void connectCities(CityNode a, CityNode b) {
        a.connectCity(b);
        b.connectCity(a);
    }

    public List<CityNode> getCityNodes() {
        return cityNodes;
    }

    public CityNode getClosestCity(City other) {
        int minDist = Integer.MAX_VALUE;
        CityNode closest = null;
        for (CityNode cityNode: cityNodes) {
            int dist = other.distance(cityNode.getCity());
            if (dist < minDist) {
                minDist = dist;
                closest = cityNode;
            }
        }
        return closest;
    }

    public int size() {
        return cityNodes.size();
    }

    public String toString() {
        return cityNodes.toString();
    }
}
