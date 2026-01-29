import java.util.*;

class Solution {
    public long minimumCost(String source, String target,
                            char[] original,
                            char[] changed,
                            int[] cost) {

        int A = 26;
        long INF = (long)1e18;

        long[][] dist = new long[A][A];

        for (int i = 0; i < A; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < original.length; i++) {
            int x = original[i] - 'a';
            int y = changed[i] - 'a';
            dist[x][y] = Math.min(dist[x][y], cost[i]);
        }

        for (int k = 0; k < A; k++) {
            for (int i = 0; i < A; i++) {
                for (int j = 0; j < A; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        long total = 0;

        for (int i = 0; i < source.length(); i++) {
            int x = source.charAt(i) - 'a';
            int y = target.charAt(i) - 'a';

            if (dist[x][y] >= INF) return -1;

            total += dist[x][y];
        }

        return total;
    }
}
