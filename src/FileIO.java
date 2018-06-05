import java.io.*;
import java.util.*;

public class FileIO {
    public static String getFileName(String[] args) {
        if (args.length != 1) {
            Scanner in = new Scanner(System.in);
            System.out.print("Filename? ");
            return in.next();
        } else {
            return args[0];
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
}
