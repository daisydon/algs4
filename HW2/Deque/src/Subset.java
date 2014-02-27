

/**
 * Created by linyu on 2/23/14.
 */
public class Subset {
    public static void main(String[] args) {
        if (args.length > 0) {
            int k = Integer.parseInt(args[0]);
            RandomizedQueue<String> queue = new RandomizedQueue<String>();
            while (!StdIn.isEmpty()) {
                queue.enqueue(StdIn.readString());
            }
            for (int i = 0; i < k; i++) {
                StdOut.println(queue.dequeue());
            }
        }
    }
}
