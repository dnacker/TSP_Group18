import java.util.List;
import java.util.Scanner;

public class NNAlgorithm {
    public static void main(String[] args) {
        String filename = FileIO.getFileName(args);
        List<City> cities = FileIO.readFile(filename);
        Path tour;
        StopWatch watch = new StopWatch();
        if (cities.size() < 1000) {
            watch.start();
            tour = NNAlgorithm.nearestNeighborSolveAllCities(cities);
            watch.stop();
        } else {
            watch.start();
            int i = 0;
            tour = NNAlgorithm.nearestNeighborSolveOneCity(cities, cities.get(i));
            while (watch.getElapsedTime() < 3 * 60 * 1000 - 1000 && i < cities.size() - 1) {
                i++;
                Path currentTour = NNAlgorithm.nearestNeighborSolveOneCity(cities, cities.get(i));
                if (currentTour.computeLength()< tour.computeLength()) {
                    tour = currentTour;
                }
            }
            watch.stop();
        }
        System.out.println("Finished in: " + watch.getElapsedTime() + " ms");
        tour.printPath(System.out);
//        FileIO.writeFile(filename + ".tour", tour);
//        GraphVisualizer graph = new GraphVisualizer(cities, tour);
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
        for (City c: cities) {
            c.reset();
        }

//        shortestPath.printPath(System.out);
        return shortestPath;
    }
}
