class Solution {
    public int minDeletionSize(String[] strs) {

        int N = strs.length;
        int L = strs[0].length();

        int[] dp = new int[L + 1];

        for (int i = L - 1; i >= 0; i--) {
            dp[i] = 1;

            for (int j = i + 1; j < L; j++) {
                boolean good = true;

                for (int k = 0; k < N; k++) {
                    if (strs[k].charAt(i) > strs[k].charAt(j)) {
                        good = false;
                        break;
                    }
                }

                if (good) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int val : dp) {
            max = Math.max(max, val);
        }

        return L - max;
    }
}
