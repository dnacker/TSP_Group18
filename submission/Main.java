import java.util.List;

public class Main {
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
        FileIO.writeFile(filename + ".tour", tour);
    }
}