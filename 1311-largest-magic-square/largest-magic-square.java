import java.util.*;

class Solution {
    public int largestMagicSquare(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;

        int[][] prefixR = new int[R][C + 1];
        int[][] prefixC = new int[R + 1][C];

        Map<Integer, int[]> prefixD1 = new HashMap<>();
        Map<Integer, int[]> prefixD2 = new HashMap<>();

        // Build prefix sums
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                prefixR[x][y + 1] = prefixR[x][y] + grid[x][y];
                prefixC[x + 1][y] = prefixC[x][y] + grid[x][y];

                prefixD1.putIfAbsent(x + y, new int[R + 1]);
                prefixD2.putIfAbsent(x - y, new int[R + 1]);

                prefixD1.get(x + y)[x + 1] =
                        prefixD1.get(x + y)[x] + grid[x][y];
                prefixD2.get(x - y)[x + 1] =
                        prefixD2.get(x - y)[x] + grid[x][y];
            }
        }

        // Try largest size first
        for (int k = Math.min(R, C); k >= 1; k--) {
            for (int x = 0; x + k <= R; x++) {
                for (int y = 0; y + k <= C; y++) {

                    boolean good = true;
                    int target = prefixR[x][y + k] - prefixR[x][y];

                    for (int d = 0; d < k && good; d++) {
                        if (prefixR[x + d][y + k] - prefixR[x + d][y] != target)
                            good = false;

                        if (prefixC[x + k][y + d] - prefixC[x][y + d] != target)
                            good = false;
                    }

                    if (!good) continue;

                    if (prefixD1.get(x + y + k - 1)[x + k]
                            - prefixD1.get(x + y + k - 1)[x] != target)
                        continue;

                    if (prefixD2.get(x - y)[x + k]
                            - prefixD2.get(x - y)[x] != target)
                        continue;

                    return k;
                }
            }
        }
        return 0;
    }
}
