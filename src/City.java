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
}
