class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;

        // prefix sums as long to avoid overflow
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (long) nums[i];
        }

        // best minimum prefix sum seen for each modulo class
        long[] bestMin = new long[k];
        Arrays.fill(bestMin, Long.MAX_VALUE);

        // For subarrays that start at index 0, prefix index is 0 => mod 0
        bestMin[0] = 0L;

        long ans = Long.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            int mod = i % k;

            // If we have seen a prefix for this modulo class, try to form subarray ending at i-1
            if (bestMin[mod] != Long.MAX_VALUE) {
                long current = prefix[i] - bestMin[mod];
                if (current > ans) ans = current;
            }

            // Update the minimum prefix for this modulo class using prefix[i]
            if (prefix[i] < bestMin[mod]) {
                bestMin[mod] = prefix[i];
            }
        }

        // Edge case: if ans remains Long.MIN_VALUE (no valid subarray) return 0 or minimal possible
        // But by problem statement there should always be some valid subarray if k >= 1.
        return ans;
    }
}
