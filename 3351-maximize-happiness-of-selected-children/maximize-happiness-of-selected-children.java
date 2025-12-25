import java.util.Arrays;

class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {

        // Sort array in ascending order
        Arrays.sort(happiness);

        long total = 0;
        int n = happiness.length;

        // Pick largest k elements from the end
        for (int i = 0; i < k; i++) {
            int value = happiness[n - 1 - i] - i;
            total += Math.max(0, value);
        }

        return total;
    }
}
