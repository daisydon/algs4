import java.util.Arrays;

/**
 * Created by linyu on 3/3/14.
 */
public class Brute {

    public static void main(String[] args) {
        if (args.length > 0) {
            In reader = new In(args[0]);
            int N = reader.readInt();
            Point[] lists = new Point[N];
            int m = 0;
            while (!reader.isEmpty()) {
                lists[m] = new Point(reader.readInt(), reader.readInt());
                lists[m].draw();
                m++;
            }
            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);

            Arrays.sort(lists);
            for (int i = 0; i < N - 3; i++) {
                Point first = lists[i];
                for (int j = i + 1; j < N - 2; j++) {
                    Point second = lists[j];
                    double slope1 = first.slopeTo(second);

                    for (int p = j + 1; p < N - 1; p++) {
                        Point third = lists[p];
                        double slope2 = first.slopeTo(third);
                        if (slope1 != slope2) continue;
                        for (int q = p + 1; q < N; q++) {
                            Point fourth = lists[q];
                            double slope3 = first.slopeTo(fourth);
                            if (slope3 != slope2) continue;
                            else {
                                //draw the line
                                first.drawTo(fourth);
                                //print out the points in order
                                StdOut.printf("%s -> %s -> %s -> %s\n", first, second, third, fourth);
                            }
                        }
                    }
                }
            }
        }
    }
}
