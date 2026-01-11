class Solution {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int R = matrix.length;
        int C = matrix[0].length;

        // Prefix sum array
        int[][] prefix = new int[R + 1][C + 1];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                prefix[i + 1][j + 1] =
                        prefix[i][j + 1]
                      + prefix[i + 1][j]
                      - prefix[i][j]
                      + (matrix[i][j] - '0');
            }
        }

        int best = 0;

        for (int upper = 0; upper < R; upper++) {
            for (int lower = upper; lower < R; lower++) {

                int full = lower - upper + 1;
                int streak = 0;

                for (int y = 0; y < C; y++) {
                    if (submatrixSum(prefix, upper, y, lower, y) == full) {
                        streak++;
                        best = Math.max(best, streak * full);
                    } else {
                        streak = 0;
                    }
                }
            }
        }
        return best;
    }

    private int submatrixSum(int[][] prefix, int ax, int ay, int bx, int by) {
        return prefix[bx + 1][by + 1]
             - prefix[ax][by + 1]
             - prefix[bx + 1][ay]
             + prefix[ax][ay];
    }
}
