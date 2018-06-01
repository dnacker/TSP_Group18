import java.util.List;

public class NNAlgorithm {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FileIO inputfile");
            System.exit(1);
        } else {
            List<City> cities = FileIO.readFile(args[0]);
            Path tour;
            if (cities.size() < 1000) {
                tour = NNAlgorithm.nearestNeighborSolveAllCities(cities);
            } else {
                tour = NNAlgorithm.nearestNeighborSolveOneCity(cities, cities.get((int)(Math.random()*cities.size())));
            }
            tour.printPath(System.out);
            FileIO.writeFile(args[0] + ".tour", tour);
        }
    }

    public static Path nearestNeighborSolveAllCities(List<City> cities) {
        Path shortestPath = null;
        long shortestPathLength = Long.MAX_VALUE;

        //compute shortest path starting from each city using NearestNeighbor Heuristic
        for (int i = 0; i < cities.size(); i++) {
            Path currentPath = nearestNeighborSolveOneCity(cities, cities.get(i));
            int currentLength = currentPath.computeLength();
            if (currentLength < shortestPathLength) {
                shortestPath = currentPath;
                shortestPathLength = currentLength;
            }
            //reset cities to !visited
            for (City c: cities) {
                c.reset();
            }
        }
        return shortestPath;
    }

    public static Path nearestNeighborSolveOneCity(List<City> cities, City start) {
        Path shortestPath = new Path();
        City curr = start;
        shortestPath.addCity(curr);
        curr.visit();

        for (int k = 0; k < cities.size() - 1; k++) {
            City nearestNeighbor = null;
            int nearestDist = Integer.MAX_VALUE;
            for (int j = 0; j < cities.size(); j++) {
                if (!cities.get(j).isVisited()) {
                    int dist = curr.distance(cities.get(j));
                    if (dist < nearestDist) {
                        nearestNeighbor = cities.get(j);
                        nearestDist = dist;
                    }
                }
            }
            nearestNeighbor.visit();
            shortestPath.addCity(nearestNeighbor);
            curr = nearestNeighbor;
        }

//        shortestPath.printPath(System.out);
        return shortestPath;
    }
}
