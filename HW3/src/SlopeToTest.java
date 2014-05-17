import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by linyu on 3/12/14.
 */
public class SlopeToTest {

    @Test
    public void testSamePoint() {
        Point p = new Point(440, 254);
        assertEquals(p.slopeTo(p), Double.NEGATIVE_INFINITY, 0.0);
    }

    @Test
    public void testHorizontal() {
        Point p = new Point(200, 300);
        Point q = new Point(400, 300);
        assertEquals(p.slopeTo(q), 0.0, 0.0);
        assertEquals(q.slopeTo(p), 0.0, 0.0);
    }

    @Test
    public void testVertical() {
        Point p = new Point(300, 300);
        Point q = new Point(300, 500);
        assertEquals(p.slopeTo(q), Double.POSITIVE_INFINITY, 0.0);
        assertEquals(q.slopeTo(p), Double.POSITIVE_INFINITY, 0.0);
    }
}
