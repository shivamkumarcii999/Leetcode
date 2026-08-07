import java.util.*;

class Solution {
    int A, B, C, D; // required exponents of 2,3,5,7
    int[][] dp;     // dp[x][y] = min extra digits to get >= x twos and >= y threes
    int[] twoContrib   = {0,0,1,0,2,0,1,0,3,0};
    int[] threeContrib = {0,0,0,1,0,0,1,0,0,2};
    int[] fiveContrib  = {0,0,0,0,0,1,0,0,0,0};
    int[] sevenContrib = {0,0,0,0,0,0,0,1,0,0};

    public String smallestNumber(String num, long t) {
        long tt = t;
        A = B = C = D = 0;
        while (tt % 2 == 0) { A++; tt /= 2; }
        while (tt % 3 == 0) { B++; tt /= 3; }
        while (tt % 5 == 0) { C++; tt /= 5; }
        while (tt % 7 == 0) { D++; tt /= 7; }
        if (tt != 1) return "-1"; // t has a prime factor other than 2,3,5,7 -> impossible

        buildDp();

        int n = num.length();
        int[] twoCount = new int[n + 1];
        int[] threeCount = new int[n + 1];
        int[] fiveCount = new int[n + 1];
        int[] sevenCount = new int[n + 1];
        boolean[] hasZero = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            int dig = num.charAt(i) - '0';
            twoCount[i + 1]   = twoCount[i]   + twoContrib[dig];
            threeCount[i + 1] = threeCount[i] + threeContrib[dig];
            fiveCount[i + 1]  = fiveCount[i]  + fiveContrib[dig];
            sevenCount[i + 1] = sevenCount[i] + sevenContrib[dig];
            hasZero[i + 1]    = hasZero[i] || (dig == 0);
        }

        // Case 1: num itself already works
        if (!hasZero[n] && twoCount[n] >= A && threeCount[n] >= B
                && fiveCount[n] >= C && sevenCount[n] >= D) {
            return num;
        }

        // Case 2: keep a prefix, bump one digit, fill the rest minimally
        for (int i = n - 1; i >= 0; i--) {
            if (hasZero[i]) continue; // prefix num[0..i-1] already has a 0, unusable
            int origDigit = num.charAt(i) - '0';
            int k = n - 1 - i;
            for (int d = origDigit + 1; d <= 9; d++) {
                int a2 = Math.max(A - twoCount[i]   - twoContrib[d], 0);
                int b2 = Math.max(B - threeCount[i] - threeContrib[d], 0);
                int c2 = Math.max(C - fiveCount[i]  - fiveContrib[d], 0);
                int d2 = Math.max(D - sevenCount[i] - sevenContrib[d], 0);
                if (feasible(a2, b2, c2, d2, k)) {
                    String suffix = fillMinimal(a2, b2, c2, d2, k);
                    return num.substring(0, i) + d + suffix;
                }
            }
        }

        // Case 3: need a longer number: build the smallest valid number of length L
        int minSlots = C + D + dp[A][B]; // minimum digits needed to satisfy A,B,C,D at all
        int L = Math.max(n + 1, minSlots);
        return fillMinimal(A, B, C, D, L);
    }

    private void buildDp() {
        dp = new int[A + 1][B + 1];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[0][0] = 0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        int[][] moves = {{1,0},{0,1},{2,0},{1,1},{3,0},{0,2}}; // digits 2,3,4,6,8,9
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            int cost = dp[x][y];
            for (int[] mv : moves) {
                int nx = Math.min(x + mv[0], A);
                int ny = Math.min(y + mv[1], B);
                if (dp[nx][ny] > cost + 1) {
                    dp[nx][ny] = cost + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    private boolean feasible(int a, int b, int c, int d, int k) {
        long needed = (long) c + d + dp[a][b];
        return needed <= k;
    }

    private String fillMinimal(int a, int b, int c, int d, int k) {
        StringBuilder sb = new StringBuilder();
        int cura = a, curb = b, curc = c, curd = d, remaining = k;
        for (int pos = 0; pos < k; pos++) {
            for (int dig = 1; dig <= 9; dig++) {
                int na = Math.max(cura - twoContrib[dig], 0);
                int nb = Math.max(curb - threeContrib[dig], 0);
                int nc = Math.max(curc - fiveContrib[dig], 0);
                int nd = Math.max(curd - sevenContrib[dig], 0);
                if (feasible(na, nb, nc, nd, remaining - 1)) {
                    sb.append((char) ('0' + dig));
                    cura = na; curb = nb; curc = nc; curd = nd;
                    remaining--;
                    break;
                }
            }
        }
        return sb.toString();
    }
}