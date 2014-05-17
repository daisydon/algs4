import java.util.Arrays;

/**
 * Created by linyu on 3/27/14.
 */

public class Board {
    private int[][] board;
    private final int N;
    private int[][] goal;   //how to make it final

    //construct a board from an N-by-N array of blocks
    public Board(int[][] blocks) {
            N = blocks.length;
            int counter = 0;
            for (int i = 0; i < N; i++) {
                counter++;
                for (int j = 0; j < N; j++) {
                    board[i][j] = blocks[i][j];
                    goal[i][j] = counter;
                }
            }
        goal[N - 1][N - 1] = 0;
    }

    //board dimension N
    public int dimension() {
        return N;
    }

    //number of blocks out of space
    public int hamming() {
        int counter = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) continue;

                else if (board[i][j] != goal[i][j]) counter++;
            }
        }
        return counter;
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) continue;
                else if (board[i][j] != goal[i][j]) {
                    int x = board[i][j] / N;
                    int y = board[i][j] % N;
                    if (y > 0) distance += Math.abs(x - i) + Math.abs(y - 1 - j);
                    else  distance += Math.abs(x - 1 - i) + Math.abs(N - 1 - j);
                }
            }
        }
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
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0 && j + 1 < N && board[i][j + 1] != 0) {
                    int tmp = board[i][j];
                    board[i][j] = tmp;
                    board[i][j] = board[i][j + 1];
                    board[i][j + 1] = tmp;
                }
            }
        }
        Board ret = new Board(board);
        return ret;
    }


    public boolean equals(Object y)        // does this board equal y?
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return (Arrays.deepEquals(this.board, that.board) && this.N == that.N);
    }

    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Stack<Board> tmp = new Stack<Board>();


        return tmp;
    }




    public String toString()               // string representation of the board (in the output format specified below)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(N);
        for (int i = 0; i < N; i++) {
            sb.append("/n");
            for (int j = 0; j < N; j++) {
                sb.append(String.format("%2d",board[i][j]));
            }
        }
        return sb.toString();
    }

}
