import java.util.*;

class Solution {

    static class State {
        int cost, x, y, k;
        State(int c, int x, int y, int k) {
            this.cost = c;
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }

    public int minCost(int[][] grid, int K) {

        int R = grid.length, C = grid[0].length;
        int INF = (int)1e9;

        Map<Integer, List<int[]>> map = new HashMap<>();
        TreeSet<Integer> values = new TreeSet<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map.computeIfAbsent(grid[i][j], v -> new ArrayList<>())
                        .add(new int[]{i, j});
                values.add(grid[i][j]);
            }
        }

        int[][][] dist = new int[R][C][K + 1];
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                Arrays.fill(dist[i][j], INF);

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        dist[0][0][0] = 0;
        pq.offer(new State(0, 0, 0, 0));

        int[][] dirs = {{0,1},{1,0}};

        TreeSet<Integer>[] unused = new TreeSet[K + 1];
        for (int i = 0; i <= K; i++)
            unused[i] = new TreeSet<>(values);

        while (!pq.isEmpty()) {

            State cur = pq.poll();
            int d = cur.cost, x = cur.x, y = cur.y, k = cur.k;

            if (x == R - 1 && y == C - 1) return d;
            if (d > dist[x][y][k]) continue;

            // normal moves
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < R && ny < C) {
                    int nd = d + grid[nx][ny];
                    if (nd < dist[nx][ny][k]) {
                        dist[nx][ny][k] = nd;
                        pq.offer(new State(nd, nx, ny, k));
                    }
                }
            }

            // teleport optimized (same as python)
            if (k < K) {
                while (!unused[k].isEmpty() && unused[k].first() <= grid[x][y]) {
                    int val = unused[k].pollFirst();

                    for (int[] pos : map.get(val)) {
                        int nx = pos[0], ny = pos[1];
                        if (d < dist[nx][ny][k + 1]) {
                            dist[nx][ny][k + 1] = d;
                            pq.offer(new State(d, nx, ny, k + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }
}
