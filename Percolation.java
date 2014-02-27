public class Percolation {

    private boolean grid[][];
    private WeightedQuickUnionUF finder;
    private WeightedQuickUnionUF copy;
    private final int HEAD;
    private final int TAIL;

    public Percolation(int N)              // create N-by-N grid, with all sites blocked
    {
        grid = new boolean[N][N];
        finder = new WeightedQuickUnionUF(N * N + 2);
        copy = new WeightedQuickUnionUF(N * N + 1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        HEAD = N * N;
        TAIL = HEAD + 1;
    }

    public void open(int i, int j)         // open site (row i, column j) if it is not already
    {
        int n = grid.length;
        int r = i - 1, c = j - 1;
        if (r >= 0 && c >= 0 && r < n && c < n) {
            grid[r][c] = true;             //mark this grid is open
            int[][] deltas = {
                    {-1, 0},
                    {0, -1},
                    {1, 0},
                    {0, 1}
            };
            for (int[] delta : deltas) {
                int nr = r + delta[0], nc = c + delta[1];
                if (nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc]) {
                    finder.union(xyTo1D(nr, nc), xyTo1D(r, c));
                    copy.union(xyTo1D(nr, nc), xyTo1D(r, c));
                }
            }

            if (r == 0) {
                finder.union(xyTo1D(r, c), HEAD);
                copy.union(xyTo1D(r, c), HEAD);
            }
            if (r == n - 1) {
                finder.union(xyTo1D(r, c), TAIL);
            }
        }
        else {
            throw new IndexOutOfBoundsException("row index i or j out of bounds");
        }

    }

   
    public boolean isOpen(int i, int j)    // is site (row i, column j) open?
    {
        int r = i - 1, c = j - 1;
        if (r >= 0 && c >= 0 && r < grid.length && c < grid.length) {
            return grid[r][c];
        }
        else {
            throw new IndexOutOfBoundsException("row index i or j out of bounds");
        }
    }

    public boolean isFull(int i, int j)    // is site (row i, column j) full?
    {
        int n = grid.length, r = i - 1, c = j - 1;

        if (r >= 0 && c >= 0 && r < n && c < n) {
            return copy.connected(xyTo1D(r, c), HEAD);
        }
        else {
            throw new IndexOutOfBoundsException("row index i or j out of bounds");
        }
    }

    public boolean percolates() {          // does the system percolate?
        return finder.connected(HEAD, TAIL);
    }

    private int xyTo1D(int r, int c) {
        return r * grid.length + c;
    }
    public static void main(String[] args) {

        
        System.out.println();
    }

}