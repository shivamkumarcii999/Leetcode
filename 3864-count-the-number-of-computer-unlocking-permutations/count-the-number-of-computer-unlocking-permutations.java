class Solution {
    private static final int MOD = 1_000_000_007;

    public int countPermutations(int[] complexity) {
        int n = complexity.length;

        // Computer 0 must have strictly smallest complexity.
        // If any other computer has complexity <= complexity[0],
        // that computer can never be unlocked -> answer = 0.
        for (int i = 1; i < n; i++) {
            if (complexity[i] <= complexity[0]) {
                return 0;
            }
        }

        // Otherwise, any permutation of [1..n-1] works,
        // so the answer is (n-1)! modulo 1e9+7.
        long ans = 1L;
        for (int i = 1; i <= n - 1; i++) {
            ans = (ans * i) % MOD;
        }

        return (int) ans;
    }
}
