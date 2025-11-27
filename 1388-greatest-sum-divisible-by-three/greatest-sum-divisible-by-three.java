import java.util.*;

class Solution {

    private int N;
    private int[] nums;
    private long[][] dp;
    private final long INF = (long)Math.pow(10, 20);

    public int maxSumDivThree(int[] nums) {

        this.N = nums.length;
        this.nums = nums;
        this.dp = new long[N + 1][3];

        // initialize dp with a marker for "not calculated"
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Long.MIN_VALUE);
        }

        long ans = f(0, 0);

        if (ans < 0) return 0;
        return (int) ans;
    }

    private long f(int index, int m) {

        if (index == N) {
            if (m == 0) {
                return 0;
            } else {
                return -INF;
            }
        }

        if (dp[index][m] != Long.MIN_VALUE) {
            return dp[index][m];
        }

        // take current element
        long take = f(index + 1, (m + nums[index]) % 3) + nums[index];

        // do not take
        long noTake = f(index + 1, m);

        long best = Math.max(take, noTake);

        dp[index][m] = best;
        return best;
    }
}
