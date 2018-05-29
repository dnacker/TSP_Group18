import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MSTAlgorithm {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MSTAlgorithm inputfile");
            System.exit(1);
        } else {
            List<City> cities = TSPFileIO.readFile(args[0]);
            CityGraph mst = generateMinimumSpanningTree(cities);
            Path path = twoApproxTour(mst);
            path.printPath(System.out);
            TSPFileIO.writeFile(args[0] + ".mstTour", path);


        }
    }

    public static CityGraph generateMinimumSpanningTree(List<City> cities) {
        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            distances.add(Integer.MAX_VALUE);
        }
        CityGraph graph = new CityGraph();

        while (!cities.isEmpty()) {
            int minIndex = 0;
            for (int i = 0; i < distances.size(); i++) {
                if (distances.get(i) < distances.get(minIndex)) {
                    minIndex = i;
                }
            }
            City closest = cities.remove(minIndex);
            distances.remove(minIndex);

            CityNode newNode = new CityNode(closest);
            if (graph.size() >= 1) {
                graph.connectCities(newNode, graph.getClosestCity(closest));
            }
            graph.addCityNode(newNode);

            for (int i = 0; i < distances.size(); i++) {
                int dist = closest.distance(cities.get(i));
                if (dist < distances.get(i)) {
                    distances.set(i, dist);
                }
            }
        }
        return graph;
    }

    public static Path twoApproxTour(CityGraph mst) {
         Path tour = new Path();
         List<City> citiesWithRepeats = new ArrayList<>();

         Stack<CityNode> toVisit = new Stack<>();
         List<CityNode> cities = mst.getCityNodes();

         CityNode curr = cities.get(0);
         toVisit.push(curr);
         curr.getCity().visit();

         while (!toVisit.empty()) {
             CityNode next = toVisit.pop();
             for (CityNode c: next.getNeighbors()) {
                 if (!c.getCity().isVisited()) {
                     c.getCity().visit();
                     toVisit.push(c);
                 }
             }
             citiesWithRepeats.add(next.getCity());
         }


         int idxOfLast = 0;
         City first = citiesWithRepeats.get(0);
         for (int i = 0; i < citiesWithRepeats.size(); i++) {
             City toAdd = citiesWithRepeats.get(i);
             if (!tour.containsCity(toAdd)) {
                 tour.addCity(toAdd, toAdd.distance(citiesWithRepeats.get(idxOfLast)));
                 idxOfLast = i;
             }
         }
         tour.addLength(first.distance(citiesWithRepeats.get(idxOfLast)));
         return tour;
    }
}
