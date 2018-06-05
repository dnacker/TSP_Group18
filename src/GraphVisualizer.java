import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphVisualizer extends JFrame {
    public static final int FRAME_WIDTH = 750;
    public static final int FRAME_HEIGHT = 750;
    public static final int SIZE = 10;
    public static final int SCALE = 1000;
    public static final int OFFSET = 50;

    public GraphVisualizer(List<City> cities, Path path) {
        super();
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new MyJPanel(cities, path);
        panel.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setContentPane(panel);
        this.setVisible(true);
    }
    class MyJPanel extends JPanel {
        private List<City> cities;
        private Path path;

        public MyJPanel(List<City> cities, Path path) {
            this.cities = cities;
            this.path = path;
        }

        public void paintComponent(Graphics g) {
            drawCities(g, cities);
            drawPath(g, path);
        }

        private void drawCities(Graphics g, List<City> cities) {
            for (City c: cities) {
                int x = (int)(1.0*c.getX()/SCALE * (FRAME_WIDTH - 2*OFFSET)) + OFFSET;
                int y = (int)(1.0*c.getY()/SCALE * (FRAME_HEIGHT- 2*OFFSET)) + OFFSET;
                g.fillOval(x, y, SIZE, SIZE);
                g.drawString(c.getName() + "", x, y);
            }
        }

        private void drawPath(Graphics g, Path path) {
            List<City> cities = path.getCities();
            for (int i = 0; i < cities.size(); i++) {
                City first = cities.get(i);
                City second;
                if (i == cities.size() - 1) {
                    second = cities.get(0);
                } else {
                    second = cities.get(i + 1);
                }
                int x1 = (int)(1.0*first.getX()/SCALE * (FRAME_WIDTH - 2*OFFSET)) + OFFSET;
                int y1 = (int)(1.0*first.getY()/SCALE * (FRAME_HEIGHT - 2*OFFSET)) + OFFSET;
                int x2 = (int)(1.0*second.getX()/SCALE * (FRAME_WIDTH - 2*OFFSET)) + OFFSET;
                int y2 = (int)(1.0*second.getY()/SCALE * (FRAME_HEIGHT - 2*OFFSET)) + OFFSET;
                g.drawLine(x1, y1, x2, y2);
            }
        }

    }
}
