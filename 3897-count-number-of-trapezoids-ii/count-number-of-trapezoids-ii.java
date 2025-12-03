import java.util.*;

class Solution {

    // Helper GCD
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public int countTrapezoids(int[][] points) {

        int N = points.length;

        // slope → map of intercept/count
        Map<String, Map<Long, Integer>> ys = new HashMap<>();

        // ---------------------------
        // 1) COUNT PARALLEL LINES
        // ---------------------------

        for (int i = 0; i < N; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];

            for (int j = i + 1; j < N; j++) {

                int x2 = points[j][0];
                int y2 = points[j][1];

                int dy = y2 - y1;
                int dx = x2 - x1;

                String slopeKey;
                long interceptKey;

                if (dx == 0) { 
                    // vertical line
                    slopeKey = "VERT";
                    interceptKey = x1;
                } 
                else if (dy == 0) { 
                    // horizontal line
                    slopeKey = "HORZ";
                    interceptKey = y1;
                } 
                else {
                    // Reduce slope using gcd
                    int g = gcd(Math.abs(dy), Math.abs(dx));
                    dy /= g;
                    dx /= g;

                    // Normalize slope sign
                    if (dx < 0) {
                        dx *= -1;
                        dy *= -1;
                    }

                    slopeKey = dy + "/" + dx;

                    // Compute intercept b = y - m*x  (store as long)
                    // Use long for safety
                    long b = (long) y1 * dx - (long) dy * x1;
                    interceptKey = b;
                }

                ys.putIfAbsent(slopeKey, new HashMap<>());
                Map<Long, Integer> map = ys.get(slopeKey);
                map.put(interceptKey, map.getOrDefault(interceptKey, 0) + 1);
            }
        }

        // ---------------------------
        // 2) COUNT TRAPEZOIDS
        // ---------------------------

        long ans = 0;

        for (String slope : ys.keySet()) {
            Map<Long, Integer> mp = ys.get(slope);

            if (mp.size() == 1) continue;

            long pairs = 0;
            for (long k : mp.keySet()) {
                pairs += mp.get(k);
            }

            for (long k : mp.keySet()) {
                long cnt = mp.get(k);
                ans += cnt * (pairs - cnt);
            }
        }

        // ---------------------------
        // 3) COUNT PARALLELOGRAMS (MIDPOINT METHOD)
        // ---------------------------

        Map<String, Map<String, Integer>> mid = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];

            for (int j = i + 1; j < N; j++) {

                int x2 = points[j][0];
                int y2 = points[j][1];

                int dy = y2 - y1;
                int dx = x2 - x1;

                String slopeKey;

                if (dx == 0) {
                    slopeKey = "VERT";
                } 
                else if (dy == 0) {
                    slopeKey = "HORZ";
                } 
                else {
                    if (dx < 0) { dx *= -1; dy *= -1; }
                    int g = gcd(Math.abs(dy), Math.abs(dx));
                    dy /= g;
                    dx /= g;
                    slopeKey = dy + "/" + dx;
                }

                // midpoint stored as key string
                int mx = x1 + x2;
                int my = y1 + y2;

                String midKey = mx + "," + my;

                mid.putIfAbsent(midKey, new HashMap<>());
                Map<String, Integer> map = mid.get(midKey);
                map.put(slopeKey, map.getOrDefault(slopeKey, 0) + 1);
            }
        }

        long p = 0;

        for (String m : mid.keySet()) {
            Map<String, Integer> mp = mid.get(m);

            if (mp.size() == 1) continue;

            long total = 0;
            for (String s : mp.keySet()) {
                total += mp.get(s);
            }

            for (String s : mp.keySet()) {
                long v = mp.get(s);
                p += v * (total - v);
            }
        }

        // Final formula
        return (int) (ans / 2 - p / 2);
    }
}
