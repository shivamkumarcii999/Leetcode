import java.util.*;

class Solution {
    static final int MOD = 1000000007;

    List<int[]> p = new ArrayList<>();
    List<List<Integer>> np = new ArrayList<>();
    int[][] dp;
    int N;

    public int numOfWays(int n) {
        this.N = n;

        // Step 1: Generate valid rows
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    for (int k = 0; k < 3; k++) {
                        if (j != k) {
                            p.add(new int[]{i, j, k});
                        }
                    }
                }
            }
        }

        // Step 2: Build transitions
        for (int i = 0; i < p.size(); i++) {
            np.add(new ArrayList<>());
            for (int j = 0; j < p.size(); j++) {
                boolean good = true;
                for (int k = 0; k < 3; k++) {
                    if (p.get(i)[k] == p.get(j)[k]) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    np.get(i).add(j);
                }
            }
        }

        // Step 3: DP array
        dp = new int[N][p.size()];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Step 4: Start DP
        long total = 0;
        for (int i = 0; i < p.size(); i++) {
            total = (total + dfs(1, i)) % MOD;
        }

        return (int) total;
    }

    private int dfs(int index, int prevRow) {
        if (index == N) return 1;

        if (dp[index][prevRow] != -1) {
            return dp[index][prevRow];
        }

        long total = 0;
        for (int next : np.get(prevRow)) {
            total = (total + dfs(index + 1, next)) % MOD;
        }

        dp[index][prevRow] = (int) total;
        return dp[index][prevRow];
    }
}
