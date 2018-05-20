import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<City> cities;
    private int length;

    public Path() {
        cities = new ArrayList<>();
        length = 0;
    }

    public void printPath(PrintStream out) {
        out.println(length);
        for (City c : cities) {
            out.println(c.getName());
        }
    }

    public void addCity(City c, int l) {
        cities.add(c);
        addLength(l);
    }

    public void addLength(int l) {
        length += l;
    }

    public int getLength() {
        return length;
    }
}
