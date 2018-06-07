import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<City> cities;

    public Path() {
        cities = new ArrayList<>();
    }

    public void printPath(PrintStream out) {
        out.println(computeLength());
        for (City c : cities) {
            out.println(c.getName());
        }
    }

    public void addCity(City c) {
        cities.add(c);
    }

    public boolean containsCity(City other) {
        boolean contains = false;
        for (City c: cities) {
            if (c.equals(other)) {
                return true;
            }
        }
        return false;
    }

    public List<City> getCities() {
        return cities;
    }

    public int computeLength() {
        int length = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            length += cities.get(i).distance(cities.get(i+1));
        }
        length += cities.get(cities.size() - 1).distance(cities.get(0));
        return length;
    }
}
