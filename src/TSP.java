import java.io.*;
import java.util.*;

public class TSP {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java TSP inputfile");
            System.exit(1);
        } else {
            List<City> cities = readFile(args[0]);
            Path tour;
            if (cities.size() < 1000) {
                tour = nearestNeighborSolveAllCities(cities);
            } else {
                tour = nearestNeighborSolveOneCity(cities, cities.get((int)(Math.random()*cities.size())));
            }
            tour.printPath(System.out);
            writeFile(args[0] + ".tour", tour);
        }
    }


    public static List<City> readFile(String fileName) {
        List<City> cities = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("input/" + fileName));
            String line = br.readLine();
            while (line != null) {
                Scanner sc = new Scanner(line);
                cities.add(new City(sc.nextInt(), sc.nextInt(), sc.nextInt()));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return cities;
    }

    public static void writeFile(String fileName, Path p) {
        PrintStream out = null;
        try {
            out = new PrintStream(new File("output/" + fileName));
            p.printPath(out);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static Path nearestNeighborSolveAllCities(List<City> cities) {
        Path shortestPath = null;
        long shortestPathLength = Long.MAX_VALUE;

        //compute shortest path starting from each city using NearestNeighbor Heuristic
        for (int i = 0; i < cities.size(); i++) {
            Path currentPath = nearestNeighborSolveOneCity(cities, cities.get(i));
            if (currentPath.getLength() < shortestPathLength) {
                shortestPath = currentPath;
                shortestPathLength = currentPath.getLength();
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
        shortestPath.addCity(curr, 0);
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
            shortestPath.addCity(nearestNeighbor, nearestDist);
            curr = nearestNeighbor;
        }
        shortestPath.addLength(curr.distance(start));

//        shortestPath.printPath(System.out);
        return shortestPath;
    }

}
