import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by linyu on 3/27/14.
 */

public class Board {
    private short[][] board;
    private final int N;

    private int cachedMahattan;


    //construct a board from an N-by-N array of blocks
    public Board(int[][] blocks) {
            N = blocks.length;
            board = new short[N][N];


            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = (short) blocks[i][j];
                }
            }
    }

    private Board(short[][] blocks) {
        N = blocks.length;
        board = new short[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }

    //board dimension N
    public int dimension() {
        return N;
    }


    //number of blocks out of space
    public int hamming() {
        int ret = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0 && board[i][j] != i * N + j + 1) ret++;
            }
        }
        return ret;
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) continue;
                else if (board[i][j] != i * N + j + 1) {
                    int x = board[i][j] / N;
                    int y = board[i][j] % N;
                    if (y > 0) distance += Math.abs(x - i) + Math.abs(y - 1 - j);
                    else  distance += Math.abs(x - 1 - i) + Math.abs(N - 1 - j);
                }
            }
        }
        //Optimization
        cachedMahattan = distance;
        return distance;
    }

    public boolean isGoal()                // is this board the goal board?
    {
        int counter = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                counter++;
                if (i == N - 1 && j == N - 1) {
                    if (board[i][j] != 0) return false;
                }
                else if (board[i][j] != counter) return false;
            }
        }
        return true;
    }

    public Board twin()                    // a board obtained by exchanging two adjacent blocks in the same row
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0 && j + 1 < N && board[i][j + 1] != 0) {
                    Board twin = new Board(board);
                    swapInRow(twin.board, new Dimension(i, j), new Dimension(i, j+1));
                    return twin;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private void swapInRow(short[][] b, Dimension d1, Dimension d2)
    {
        short tmp = b[d1.x][d1.y];
        b[d1.x][d1.y] = b[d2.x][d2.y];
        b[d2.x][d2.y] = tmp;
    }


    public boolean equals(Object y)        // does this board equal y?
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return (Arrays.deepEquals(this.board, that.board) && this.N == that.N);
    }

    private class Dimension {
        private int x;
        private int y;
        public Dimension(int m, int n) {
            this.x = m;
            this.y = n;
        }
        public Dimension setX(int m) {
             this.x = m;
             return this;
        }
        public Dimension setY(int n) {
            this.y = n;
            return this;
        }

    }

    private Dimension find(short[][] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == value) {
                    return new Dimension(i, j);
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Dimension zero = find(board, 0);
        ArrayList<Board> ret = new ArrayList<Board>();
        if (zero.x >= 0 && zero.y >= 0) {
            //up
            if (zero.y - 1 >= 0) {
                Board tmp = new Board(board);
                swapWithZero(tmp.board, zero, new Dimension(zero.x, zero.y - 1));
                ret.add(tmp);
            }
            //down
            if (zero.y + 1 < N) {
                Board tmp = new Board(board);
                swapWithZero(tmp.board, zero, new Dimension(zero.x, zero.y + 1));
                ret.add(tmp);
            }
            //left
            if (zero.x - 1 >= 0) {
                Board tmp = new Board(board);
                swapWithZero(tmp.board, zero, new Dimension(zero.x - 1, zero.y));
                ret.add(tmp);
            }
            //right
            if (zero.x + 1 < board[0].length) {
                Board tmp = new Board(board);
                swapWithZero(tmp.board, zero, new Dimension(zero.x + 1, zero.y));
                ret.add(tmp);
            }
            return ret;
        }
        throw new IllegalArgumentException();
    }

    private void swapWithZero(short[][] b, Dimension zero, Dimension d) {
        b[zero.x][zero.y] = b[d.x][d.y];
        b[d.x][d.y] = 0;
    }


    public String toString()               // string representation of the board (in the output format specified below)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(N);
        sb.append('\n');
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(String.format(String.format("%%%dd", 1 + digits(N * N - 1)), board[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private static int digits(int k)
    {
        int nd = 1;
        int remain = k;

        while (remain >= 10) {
            nd++;
            remain /= 10;
        }
        return nd;
    }

}
