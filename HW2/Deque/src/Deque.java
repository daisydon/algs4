import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * Created by linyu on 2/23/14.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node head;         //head of linked list
    private Node tail;         //tail of linked list
    private int N;

    private class Node {
        private Item val;
        private Node next;

        public Node() {
            next = null;
        }
        public Node val(Item value) {
            this.val = value;
            return this;
        }
        public Node next(Node node) {
            this.next = node;
            return this;
        }
    }

    public Deque() {                         // construct an empty deque
        head = null;
        tail = null;
        N = 0;
    }

    public boolean isEmpty() {               // is the deque empty?
        return N == 0;
    }

    public int size() {                      // return the number of items on the deque
        return N;
    }

    public void addFirst(Item item) {        // insert the item at the front
        if (item == null) throw new NullPointerException();
        if (isEmpty()) {
            head = new Node().val(item);
            N++;
        }
        else {
            Node tmp = new Node().val(item);
            Node copy = head;
            head = tmp;
            head.next = copy;
            //tail is null, only one node in the linked list, after add before head, tail should point to old head, head point to new node
            if (tail == null) {
                tail = copy;
            }
            N++;
        }
    }


    public void addLast(Item item) {         // insert the item at the end
        if (item == null) throw new NullPointerException();
        if (isEmpty()) {
            head = new Node().val(item);
            N++;
        }
        else {
            Node tmp = new Node().val(item);
            if (tail == null) {
                tail = tmp;
                head.next = tail;
            }
            else {
                tail.next = tmp;
                tail = tail.next;
            }
            N++;
        }
    }

    public Item removeFirst() {              // delete and return the item at the front
        if (isEmpty()) throw new NoSuchElementException();
        Item item = head.val;
        N--;
        if (N == 1) {
            head = head.next;
            tail = tail.next;
        }
        else {
            head = head.next;
        }
        return item;
    }

    public Item removeLast() {               // delete and return the item at the end
        if (isEmpty()) throw new NoSuchElementException();
        Item item;
        N--;
        if (N == 1) {
            item = tail.val;
            tail = tail.next;
            head.next = tail;
        }
        else if (N == 0) {
            item = head.val;
            head = head.next;
        }
        else {
            item = tail.val;
            Node tmp = head;
            while (tmp != null && tmp.next != tail) {
                tmp = tmp.next;
            }
            tmp.next = null;
            tail = tmp;
        }
        return item;
    }

    public Iterator<Item> iterator() {       // return an iterator over items in order from front to end
        return new ListIterator();

    }

    private class ListIterator implements Iterator<Item> {
        private Node front = head;

        public boolean hasNext() {
            return front != null;
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = front.val;
            front = front.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) { // unit testing


    }
}
