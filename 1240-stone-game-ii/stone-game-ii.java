class Solution {
    private int[][] memo;
    private int[] suffixSum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        memo = new int[n][n + 1];
        suffixSum = new int[n];
        
        // Compute suffix sums to quickly find the sum of remaining piles
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        return dfs(0, 1, piles);
    }

    private int dfs(int i, int m, int[] piles) {
        // If we have reached or passed the end of the piles, no stones left
        if (i == piles.length) {
            return 0;
        }
        
        // If remaining piles can all be taken by the current player
        if (i + 2 * m >= piles.length) {
            return suffixSum[i];
        }
        
        // Check memoization table
        if (memo[i][m] != 0) {
            return memo[i][m];
        }
        
        int maxStones = 0;
        
        // Try all valid choices for X from 1 to 2M
        for (int x = 1; x <= 2 * m; x++) {
            // The stones the current player gets from current turn plus
            // whatever is left minus what the opponent can maximally get from the remaining piles
            int opponentStones = dfs(i + x, Math.max(m, x), piles);
            int currentStones = suffixSum[i] - opponentStones;
            maxStones = Math.max(maxStones, currentStones);
        }
        
        memo[i][m] = maxStones;
        return maxStones;
    }
}