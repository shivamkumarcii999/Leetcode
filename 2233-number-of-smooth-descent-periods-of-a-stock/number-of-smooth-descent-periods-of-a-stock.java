class Solution {
    public long getDescentPeriods(int[] prices) {
        long total = 1;
        long streak = 1;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] - 1 == prices[i]) {
                streak++;
            } else {
                streak = 1;
            }
            total += streak;
        }

        return total;
    }
}
