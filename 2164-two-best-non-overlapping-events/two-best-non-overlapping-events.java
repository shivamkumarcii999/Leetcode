import java.util.*;

class Solution {
    public int maxTwoEvents(int[][] events) {

        long INF = (long) 1e18;
        List<long[]> evts = new ArrayList<>();

        // Create start and end events
        for (int[] e : events) {
            int s = e[0], end = e[1], v = e[2];
            evts.add(new long[]{s, -1, v});   // start event
            evts.add(new long[]{end, 1, v});  // end event
        }

        // Sort events
        Collections.sort(evts, (a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });

        long best = -INF;
        long bestOneEvent = -INF;

        // Sweep line
        for (long[] e : evts) {
            long t = e[1];
            long v = e[2];

            if (t == -1) {
                // start event
                best = Math.max(best, bestOneEvent + v);
            } else {
                // end event
                bestOneEvent = Math.max(bestOneEvent, v);
                best = Math.max(best, v);
            }
        }

        return (int) best;
    }
}
