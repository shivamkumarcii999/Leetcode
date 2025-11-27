class Solution {
    boolean vis[];

    public int removeStones(int[][] arr) {
        int n = arr.length;
        vis = new boolean[n]; // to visit each stone

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i, arr);
                ans++;
            }
        }
        return n - ans;
    }

    void dfs(int st, int arr[][]) {
        if (!vis[st]) {
            vis[st] = true;

            for (int i = 0; i < arr.length; i++) {

                // if row or column match, consider same component
                if (arr[st][0] == arr[i][0] || arr[st][1] == arr[i][1]) {
                    dfs(i, arr);
                }
            }
        }
        return;
    }
}
