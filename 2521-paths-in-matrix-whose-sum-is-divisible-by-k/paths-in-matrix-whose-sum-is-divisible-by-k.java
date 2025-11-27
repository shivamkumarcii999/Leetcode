class Solution {
    private static final int MOD = 1000000007;
    private int[][] grid;
    private int R, C, K;
    private Integer[][][] dp;

    public int numberOfPaths(int[][] grid, int k) {
        this.grid = grid;
        this.K = k;
        this.R = grid.length;
        this.C = grid[0].length;

        dp = new Integer[R][C][k];

        return solve(0, 0, grid[0][0] % K);
    }

    private int solve(int x, int y, int mod) {
        // Base case: reached bottom-right cell
        if (x == R - 1 && y == C - 1) {
            return (mod == 0) ? 1 : 0;
        }

        // Memoization check
        if (dp[x][y][mod] != null) {
            return dp[x][y][mod];
        }

        long total = 0;

        // Go right
        if (y + 1 < C) {
            int newMod = (mod + grid[x][y + 1]) % K;
            total += solve(x, y + 1, newMod);
        }

        // Go down
        if (x + 1 < R) {
            int newMod = (mod + grid[x + 1][y]) % K;
            total += solve(x + 1, y, newMod);
        }

        return dp[x][y][mod] = (int)(total % MOD);
    }
}
