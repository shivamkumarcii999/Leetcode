import java.util.*;

class Solution {
    public int specialTriplets(int[] nums) {
        int N = nums.length;
        long MOD = 1000000007L;

        // Left and Right frequency maps
        HashMap<Integer, Long> fleft = new HashMap<>();
        HashMap<Integer, Long> fright = new HashMap<>();

        // Fill right frequency map
        for (int num : nums) {
            fright.put(num, fright.getOrDefault(num, 0L) + 1);
        }

        long total = 0;

        for (int j = 0; j < N; j++) {
            int val = nums[j];

            // Decrease current from right
            fright.put(val, fright.get(val) - 1);

            int key = val * 2;

            long leftCount = fleft.getOrDefault(key, 0L);
            long rightCount = fright.getOrDefault(key, 0L);

            total = (total + (leftCount * rightCount) % MOD) % MOD;

            // Add current to left
            fleft.put(val, fleft.getOrDefault(val, 0L) + 1);
        }

        return (int) (total % MOD);
    }
}
