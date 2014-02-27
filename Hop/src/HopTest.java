import java.util.Arrays;

/**
 * Created by linyu on 2/26/14.
 */
public class HopTest {

    public static void main(String[] args) {

        Hop h = new Hop();
        assert Arrays.deepEquals(
                h.jump(Arrays.asList(5, 6, 0, 4, 2, 4, 1, 0, 0, 4)).toArray(),
                new Object[] {0, 5, 9}
        );

        System.out.println("All tests passed!");

    }

}
