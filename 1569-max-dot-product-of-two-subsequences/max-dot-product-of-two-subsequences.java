import java.util.*;

class Solution {

    int[] nums1, nums2;
    int n1, n2;
    Integer[][][] dp;
    final int NEG_INF = (int) -1e9;

    public int maxDotProduct(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.n1 = nums1.length;
        this.n2 = nums2.length;

        dp = new Integer[n1][n2][2];

        return solve(0, 0, 0);
    }

    private int solve(int i, int j, int picked) {
        // Base case
        if (i == n1 || j == n2) {
            return picked == 1 ? 0 : NEG_INF;
        }

        if (dp[i][j][picked] != null) {
            return dp[i][j][picked];
        }

        // Option 1: pick current elements
        int take = nums1[i] * nums2[j] + solve(i + 1, j + 1, 1);

        // Option 2: skip nums1[i]
        int skip1 = solve(i + 1, j, picked);

        // Option 3: skip nums2[j]
        int skip2 = solve(i, j + 1, picked);

        dp[i][j][picked] = Math.max(take, Math.max(skip1, skip2));
        return dp[i][j][picked];
    }
}
