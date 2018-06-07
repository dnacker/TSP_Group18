public class Main {
    public static void main(String[] args) {
        String filename = FileIO.getFileName(args);
        List<City> cities = FileIO.readFile(filename);
        Path tour;
        if (cities.size() < 1000) {
            tour = NNAlgorithm.nearestNeighborSolveAllCities(cities);
        } else {
            tour = NNAlgorithm.nearestNeighborSolveOneCity(cities, cities.get((int)(Math.random()*cities.size())));
        }
        tour.printPath(System.out);
        FileIO.writeFile(filename + ".tour", tour);
    }
}