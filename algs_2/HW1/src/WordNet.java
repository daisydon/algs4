import java.util.Iterator;

/**
 * Created by linyu on 4/30/14.
*/
public class WordNet {

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {

    }
    // the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns() {
        return new wordIterator();
    }

    private class wordIterator implements Iterable<String> {

        public boolean hasNext() {

        }

        public String next() {

        }

        public void remove() {

        }
    }
    // is the word a WordNet noun?
    public boolean isNoun(String word) {

    }
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {

    }
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    public String sap() {

    }
    // for unit testing of this class
    public static void main(String[] args) {

    }
}
