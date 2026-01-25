import java.util.*;

class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;

        // Sort the array
        Arrays.sort(nums);

        int INF = (int)1e9;
        int best = INF;

        // Sliding window of size k
        for (int i = 0; i <= n - k; i++) {
            best = Math.min(best, nums[i + k - 1] - nums[i]);
        }

        return best;
    }
}
