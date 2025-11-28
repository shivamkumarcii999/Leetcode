import java.util.*;

class Solution {
    public int maxKDivisibleComponents(int N, int[][] edges, int[] values, int k) {

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int[] count = new int[1];  // number of valid divisible components

        class DFS {
            long solve(int node, int parent) {
                long total = values[node];

                for (int nxt : adj.get(node)) {
                    if (nxt == parent) continue;

                    long subtree = solve(nxt, node);

                    if (subtree % k == 0) {
                        count[0]++;      // this subtree itself is 1 component
                    } else {
                        total += subtree; // merge upward
                    }
                }
                return total;
            }
        }

        DFS dfs = new DFS();
        long rootTotal = dfs.solve(0, -1);

        // Root also forms a component if divisible
        if (rootTotal % k == 0) count[0]++;

        return count[0];
    }
}
