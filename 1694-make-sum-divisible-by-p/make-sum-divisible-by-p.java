import java.util.*;

class Solution {
    public int minSubarray(int[] nums, int p) {
        int N = nums.length;

        long total = 0;
        for (int x : nums) total += x;

        int k = (int)(total % p);
        if (k == 0) return 0;   // Already divisible

        int INF = (int)1e9;

        // Map: prefixSum % p → last index of that prefix
        Map<Integer, Integer> prev = new HashMap<>();
        prev.put(0, -1);

        long current = 0;
        int best = INF;

        for (int index = 0; index < N; index++) {
            current += nums[index];
            int currMod = (int)(current % p);

            prev.put(currMod, index);

            int need = (currMod - k + p) % p;
            if (prev.containsKey(need)) {
                best = Math.min(best, index - prev.get(need));
            }
        }

        return best >= N ? -1 : best;
    }
}
