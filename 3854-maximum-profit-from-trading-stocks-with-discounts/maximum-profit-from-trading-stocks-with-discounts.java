import java.util.*;

class Solution {

    int n, budget;
    int[] present, future;
    List<Integer>[] tree;

    // Memoization
    Map<String, int[]> memoF = new HashMap<>();
    Map<String, int[]> memoC = new HashMap<>();

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        this.n = n;
        this.present = present;
        this.future = future;
        this.budget = budget;

        // Build tree
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        boolean[] isChild = new boolean[n + 1];
        for (int[] h : hierarchy) {
            tree[h[0]].add(h[1]);
            isChild[h[1]] = true;
        }

        // Find root (CEO)
        int root = 1;
        for (int i = 1; i <= n; i++) {
            if (!isChild[i]) {
                root = i;
                break;
            }
        }

        int[] ans = dfs(root, false);
        return ans[budget];
    }

    // dfs(node, parentBought)
    private int[] dfs(int node, boolean parentBought) {
        String key = node + "-" + parentBought;
        if (memoF.containsKey(key)) return memoF.get(key);

        // Case 1: do not buy current node
        int[] res = combine(node, 0, false);

        // Case 2: buy current node
        int cost = parentBought ? present[node - 1] / 2 : present[node - 1];
        int profit = future[node - 1] - cost;

        if (cost <= budget) {
            int[] childDP = combine(node, 0, true);
            for (int b = 0; b + cost <= budget; b++) {
                res[b + cost] = Math.max(res[b + cost], childDP[b] + profit);
            }
        }

        memoF.put(key, res);
        return res;
    }

    // combine children using knapsack-style DP
    private int[] combine(int node, int idx, boolean parentBought) {
        String key = node + "-" + idx + "-" + parentBought;
        if (memoC.containsKey(key)) return memoC.get(key);

        if (idx == tree[node].size()) {
            return new int[budget + 1];
        }

        int child = tree[node].get(idx);
        int[] skip = combine(node, idx + 1, parentBought);
        int[] take = dfs(child, parentBought);

        int[] best = new int[budget + 1];
        for (int i = 0; i <= budget; i++) {
            for (int j = 0; i + j <= budget; j++) {
                best[i + j] = Math.max(best[i + j], skip[i] + take[j]);
            }
        }

        memoC.put(key, best);
        return best;
    }
}
