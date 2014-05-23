import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Solver {

    private final List<Board> solution;


    public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
    {
        solution = solve(initial);
    }

    public boolean isSolvable()             // is the initial board solvable?
    {
        return solution != null;
    }

    public int moves()                      // min number of moves to solve initial board; -1 if no solution
    {
        if (solution == null) return -1;
        else
            return solution.size() - 1;
    }

    public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
    {
        return solution;
    }

    private List<Board> solve(Board init) {
        MinPQ<Node> origin = new MinPQ<Node>(1, Node.MANHATTAN);
        MinPQ<Node> twin = new MinPQ<Node>(1, Node.MANHATTAN);

        origin.insert(new Node(init, null, 0));
        twin.insert(new Node(init.twin(), null, 0));

        List<SolutionInfo> candidates = Arrays.asList(new TrueSolution(origin), new VirtualSolution(twin));

        try {
            while (noneTerminate(candidates)) {
                for (SolutionInfo candidate : candidates) {
                    candidate.bfs();
                }
            }
        }
        catch (SolutionFoundException e) {
            return e.solution;
        }
        throw new IllegalStateException();
    }

    private boolean noneTerminate(List<SolutionInfo> candidates) {
        for (SolutionInfo candidate: candidates) {
            if (candidate.isTerminal()) {
                return false;
            }
        }
        return true;
    }

    private final class TrueSolution extends SolutionInfo {

        public TrueSolution(MinPQ<Node> queue) {
            super(queue);
        }

        @Override
        public List<Board> getPath(Node goal) {
            List<Board> list = new ArrayList<Board>();

            Node cur = goal;
            while (cur != null) {
                list.add(cur.board);
                cur = cur.pre;
            }
            Collections.reverse(list);
            return list;
        }
    }

    private final class VirtualSolution extends SolutionInfo {

        public VirtualSolution(MinPQ<Node> queue) {
            super(queue);
        }

        @Override
        public List<Board> getPath(Node goal) {
            return null;
        }
    }
    private abstract class SolutionInfo {
        private final MinPQ<Node> queue;

        public SolutionInfo(MinPQ<Node> q) {
            this.queue = q;
        }

        public boolean isTerminal() {
            return queue.isEmpty();
        }

        public void bfs() throws SolutionFoundException {
            Node min = queue.delMin();
            if (min.board.isGoal()) {
                throw new SolutionFoundException(getPath(min));
            } else {
                for (Board b: min.board.neighbors()) {
                    if (min.pre == null || !min.pre.board.equals(b)) {
                        queue.insert(new Node(b, min, min.moves + 1));
                    }
                }
            }
        }

        public abstract List<Board> getPath(Node goal);
    }


    private final class SolutionFoundException extends Exception {
        private final List<Board> solution;

        public SolutionFoundException(List<Board> solution) {
            this.solution = solution;
        }

        public List<Board> getSolution() {
            if (solution == null) return null;
            else return Collections.unmodifiableList(solution);
        }
    }

    private static final class Node {

        public static final Comparator<Node> MANHATTAN = new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return (node1.board.manhattan() + node1.moves) - (node2.board.manhattan() + node2.moves);
            }
        };

        private Board board;
        private Node pre;
        private int moves;



        public Node(Board b, Node p, int m) {
            this.board = b;
            this.pre = p;
            this.moves = m;
        }

        public Board getBoard() {
            return board;
        }

        public Node getPre() {
            return pre;
        }

        public int getMoves() {
            return moves;
        }
    }

    public static void main(String[] args)  // solve a slider puzzle (given below)
    {

    }

}
