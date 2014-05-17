import java.util.*;

/**
 * Created by linyu on 3/27/14.
 */

public class Solver {
    private Board initState;
    private List<Board> Solution;


    public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
    {
        this.Solution = solve(initial);

    }

    public boolean isSolvable()             // is the initial board solvable?
    {
        return Solution.size() != 0;
    }

    public int moves()                      // min number of moves to solve initial board; -1 if no solution
    {
        if (Solution.size() == 0) return -1;
        return Solution.size() - 1;
    }

    public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
    {
        return Solution;
    }

    private List<Board> solve(Board initial) {
        List<Board> ret = new LinkedList<Board>();

        return ret;
    }

    private class Node {
        private Board board;
        private Node pre;
        private int moves;

        public final Comparator<Node> MANHATTAN = new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return (node1.board.manhattan() + node1.moves) - (node2.board.manhattan() + node2.moves);
            }
        };

        public Node(Board b, Node p, int m) {
            this.board = b;
            this.pre = p;
            this.moves = m;
        }

        public void setMoves(int m) {
            this.moves = m;
        }

        public Node pre() {
            return this.pre;
        }

        public int steps() {
            return this.moves;
        }

        public Board board() {
            return this.board;
        }
    }

    public static void main(String[] args)  // solve a slider puzzle (given below)
    {

    }

}
