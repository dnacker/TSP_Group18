import java.awt.*;

public class City {
    private int xCoord;
    private int yCoord;
    private int name;
    private boolean visited;

    public City(int n, int x, int y) {
        name = n;
        xCoord = x;
        yCoord = y;
        visited = false;
    }

    public int distance(City other) {
        return (int)Math.round(Math.sqrt((other.yCoord - this.yCoord) * (other.yCoord - this.yCoord) +
                (other.xCoord - this.xCoord) * (other.xCoord - this.xCoord)));
    }

    public int getX() {
        return xCoord;
    }

    public int getY() {
        return yCoord;
    }

    public void visit() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public void reset() {
        visited = false;
    }

    public int getName() {
        return name;
    }

    public boolean equals(Object o) {
        if (o instanceof City) {
            return name == ((City)o).name;
        }
        return false;
    }

    public String toString() {
        return name + "";
    }
}
