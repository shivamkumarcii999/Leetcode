class Solution {

    public int maxSideLength(int[][] mat, int threshold) {
        int R = mat.length;
        int C = mat[0].length;

        // Prefix sum array (R+1 x C+1)
        int[][] prefix = new int[R + 1][C + 1];

        // Row-wise prefix
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                prefix[i + 1][j + 1] = prefix[i + 1][j] + mat[i][j];
            }
        }

        // Column-wise accumulation
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                prefix[i + 1][j + 1] += prefix[i][j + 1];
            }
        }

        int left = 0;
        int right = Math.min(R, C) + 1;

        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (good(prefix, R, C, mid, threshold)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private boolean good(int[][] prefix, int R, int C, int target, int threshold) {
        for (int i = 0; i <= R - target; i++) {
            for (int j = 0; j <= C - target; j++) {
                int sum = prefix[i + target][j + target]
                        - prefix[i + target][j]
                        - prefix[i][j + target]
                        + prefix[i][j];

                if (sum <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
}
