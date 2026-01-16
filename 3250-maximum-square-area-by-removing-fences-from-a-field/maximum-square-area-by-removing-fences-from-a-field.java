import java.util.*;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {

        final int MOD = 1_000_000_007;

        // Convert to lists
        List<Integer> h = new ArrayList<>();
        List<Integer> v = new ArrayList<>();

        for (int x : hFences) h.add(x);
        for (int x : vFences) v.add(x);

        // Add boundaries
        h.add(1);
        h.add(m);
        v.add(1);
        v.add(n);

        // Sort
        Collections.sort(h);
        Collections.sort(v);

        // Compute all horizontal gaps
        Set<Long> hGaps = new HashSet<>();
        for (int i = 0; i < h.size(); i++) {
            for (int j = i + 1; j < h.size(); j++) {
                hGaps.add((long) h.get(j) - h.get(i));
            }
        }

        // Compute all vertical gaps
        Set<Long> vGaps = new HashSet<>();
        for (int i = 0; i < v.size(); i++) {
            for (int j = i + 1; j < v.size(); j++) {
                vGaps.add((long) v.get(j) - v.get(i));
            }
        }

        // Find maximum common gap
        long best = -1;
        for (long gap : hGaps) {
            if (vGaps.contains(gap)) {
                best = Math.max(best, gap);
            }
        }

        if (best == -1) return -1;

        return (int) ((best * best) % MOD);
    }
}
