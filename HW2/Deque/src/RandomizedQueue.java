import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by linyu on 2/23/14.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int N;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        N = 0;
        a = (Item[]) new Object[2];
    }

    //resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        return N == 0;
    }

    public int size()                        // return the number of items on the queue
    {
        return N;
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null) throw new NullPointerException();
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;


    }

    public Item dequeue()                    // delete and return a random item
    {
        if (isEmpty()) throw new NoSuchElementException();
        //get a random key in range[0, N - 1]
        int random = StdRandom.uniform(N);
        Item item = a[random];
        a[random] = a[N - 1];
        a[N - 1] = null;
        N--;
        //shrink size of array if necessary
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }

    public Item sample()                     // return (but do not delete) a random item
    {
        if (isEmpty()) throw new NoSuchElementException();
        //get a random key in range[0, N-1]
        int random = StdRandom.uniform(N);
        Item item = a[random];
        return item;
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new ListIterator();

    }

    private class ListIterator implements Iterator<Item> {
        private Item[] tmp;
        private int i;

        public ListIterator() {
            tmp = (Item[]) new Object[N];
            for (int j = 0; j < N; j++) {
                tmp[j] = a[j];
            }
            i = N;
            StdRandom.shuffle(tmp);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return tmp[--i];
        }
    }

    public static void main(String[] args)   // unit testing
    {

    }

}
