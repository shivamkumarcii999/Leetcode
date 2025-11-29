class Solution {
    public int minOperations(int[] nums, int k) {
        long sum = 0;

        for (int n : nums) {
            sum += n;
        }

        long rem = sum % k;

        return (int) rem;  // number of decreases needed
    }
}
