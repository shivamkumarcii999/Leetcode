import java.util.*;

class Solution {

    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;

        long NEG = Long.MIN_VALUE / 4;

        // dp[transactions][state]
        long[][] dp = new long[k + 1][3];

        for (int t = 0; t <= k; t++) {
            dp[t][0] = 0;        // neutral
            dp[t][1] = NEG;     // holding buy
            dp[t][2] = NEG;     // holding short
        }

        for (int price : prices) {
            long[][] next = new long[k + 1][3];

            for (int t = 0; t <= k; t++) {
                // stay neutral
                next[t][0] = dp[t][0];

                // buy from neutral
                next[t][1] = Math.max(dp[t][1], dp[t][0] - price);

                // short sell from neutral
                next[t][2] = Math.max(dp[t][2], dp[t][0] + price);

                if (t > 0) {
                    // sell after buy
                    next[t][0] = Math.max(next[t][0], dp[t - 1][1] + price);

                    // buy back after short
                    next[t][0] = Math.max(next[t][0], dp[t - 1][2] - price);
                }
            }

            dp = next;
        }

        long ans = 0;
        for (int t = 0; t <= k; t++) {
            ans = Math.max(ans, dp[t][0]);
        }

        return ans;
    }
}
