import java.util.List;

public class CityGraph {
    private List<City> cities;
    private int[][] distances;

    public CityGraph() {
        
    }

    public CityGraph(List<City> cities) {
        this.cities = cities;
        distances = new int[cities.size()][];
        for (int i = 0; i < cities.size(); i++) {
            distances[i] = new int[cities.size()];
            for (int j = 0; j < cities.size(); j++) {
                distances[i][j] = cities.get(i).distance(cities.get(j));
            }
        }
    }






}
