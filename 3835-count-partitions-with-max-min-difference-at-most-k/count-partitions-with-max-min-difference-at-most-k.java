import java.util.*;

class Solution {

    static final int MOD = 1_000_000_007;

    public int countPartitions(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Compute farthest valid right boundary for each left
        int[] reach = new int[n];
        Deque<Integer> minQ = new ArrayDeque<>();
        Deque<Integer> maxQ = new ArrayDeque<>();

        int r = 0;
        for (int l = 0; l < n; l++) {

            while (r < n) {
                while (!minQ.isEmpty() && nums[minQ.peekLast()] > nums[r])
                    minQ.pollLast();
                while (!maxQ.isEmpty() && nums[maxQ.peekLast()] < nums[r])
                    maxQ.pollLast();

                minQ.offerLast(r);
                maxQ.offerLast(r);

                if (nums[maxQ.peekFirst()] - nums[minQ.peekFirst()] > k) {
                    minQ.removeLast();
                    maxQ.removeLast();
                    break;
                }
                r++;
            }

            reach[l] = r - 1;

            if (!minQ.isEmpty() && minQ.peekFirst() == l) minQ.pollFirst();
            if (!maxQ.isEmpty() && maxQ.peekFirst() == l) maxQ.pollFirst();
        }

        // Step 2: DP with Prefix Sum
        long[] dp = new long[n + 1];
        long[] prefix = new long[n + 2];

        dp[n] = 1;
        prefix[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = (prefix[i + 1] - prefix[reach[i] + 2] + MOD) % MOD;
            prefix[i] = (dp[i] + prefix[i + 1]) % MOD;
        }

        return (int) dp[0];
    }
}
