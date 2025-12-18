import java.util.*;

class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;

        // Use long to avoid overflow
        long[] prefix = new long[n + 1];
        long[] prefixSell = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (long) prices[i] * strategy[i];
            prefixSell[i + 1] = prefixSell[i] + prices[i];
        }

        long total = prefix[n];
        long best = total;

        for (int i = 0; i + k <= n; i++) {
            long remove = prefix[i + k] - prefix[i];
            long add = prefixSell[i + k] - prefixSell[i + k / 2];

            best = Math.max(best, total - remove + add);
        }

        return best;
    }
}
