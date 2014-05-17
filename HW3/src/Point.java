import java.util.Comparator;

/**
 * Created by linyu on 3/3/14.
 */
public class Point implements Comparable<Point> {
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
            return Double.compare(slopeTo(p1), slopeTo(p2));
        }
    };
    private final int x;
    private final int y;

    public Point(int x, int y)                         // construct the point (x, y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw()                                 // draw this point
    {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that)                     // draw the line segment from this point to that point
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString()                           // string representation
    {
        return "(" + x + "," + y + ")";
    }

    public int compareTo(Point that)                   // is this point lexicographically smaller than that point?
    {
        if (that != null) {
            if (this.y < that.y || (this.y == that.y && this.x < that.x))
                return -1;
            else if (this.y == that.y && this.x == that.x)
                return 0;
            else
                return 1;
        }
        else {
            throw new NullPointerException();
        }
    }

    public double slopeTo(Point that)                  // the slope between this point and that point
    {
        double dy = that.y - y;
        double dx = that.x - x;
        if (dx == 0) {
            if (dy == 0) return Double.NEGATIVE_INFINITY;
            else return Double.POSITIVE_INFINITY;
        }
        else {
            if (dy == 0) return 0;
            else return dy/dx;
        }
    }
}