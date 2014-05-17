import java.util.Iterator;

/**
 * Created by linyu on 4/16/14.
 */
public class TreeStringCompare {

    private class StringNode {
        public char ch;
        public StringNode left;
        public StringNode right;
    }

    public static int compare(StringNode rootA, StringNode rootB) {
        Iterator<Character> iterA = new TreeIterator(rootA);
        Iterator<Character> iterB = new TreeIterator(rootB);

        while (iterA.hasNext() && iterB.hasNext()) {
            switch (iterA.next().compareTo(iterB.next())) {
                case 1: {
                    return 1;
                }
                case -1: {
                    return -1;
                }
            }
        }

        if (iterA.hasNext()) {
            return 1;
        } else {
            return iterB.hasNext() ? -1 : 0;
        }
    }

    private static class TreeIterator implements Iterator<Character> {

        private final Stack<StringNode> mNodes;
        private final Stack<Character> mChars;

        public TreeIterator(StringNode root) {
            mNodes = new Stack<StringNode>();
            mChars = new Stack<Character>();
            mNodes.push(root);
        }

        @Override
        public boolean hasNext() {
            return !(mChars.isEmpty() && mNodes.isEmpty());
        }

        @Override
        public Character next() {

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
