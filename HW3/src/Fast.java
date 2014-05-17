import java.util.Arrays;

/**
 * Created by linyu on 3/3/14.
 */
public class Fast {
    public static void main(String[] args) {
        if (args.length > 0) {
            In reader = new In(args[0]);
            int N = reader.readInt();
            Point[] points = new Point[N];

            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);

            for (int i = 0; !reader.isEmpty(); i++) {
                points[i] = new Point(reader.readInt(), reader.readInt());
                points[i].draw();
            }

            //sort the points
            Arrays.sort(points);

            Point[] list = new Point[points.length - 1];
            for (int j = 0; j < points.length; j++) {
                Point p = points[j];  //origin point p

                int counter = 0;
                for(Point q : points) {
                    if (p != q) {
                        list[counter] = q;
                        counter++;
                    }
                }

                Arrays.sort(list, p.SLOPE_ORDER);
                findCollinear(p, list);
            }
        }

    }

    private static void findCollinear(Point p, Point[] points) {
        int i = 0;
        while (i < points.length) {
            Point first = points[i];
            int j = i + 1;

            boolean skip = first.compareTo(p) <= 0;
            while (j < points.length && p.slopeTo(points[j]) == p.slopeTo(first)) {
                skip = skip || points[j].compareTo(p) <= 0;
                j++;
            }

            if (!skip && j - i > 2) {
                p.drawTo(points[j - 1]);
                StdOut.print(p);

                for(int m = i; m < j; m++) {
                    StdOut.print("->");
                    StdOut.print(points[m]);
                }
                StdOut.println();
            }
            i = j;
        }
    }


}
