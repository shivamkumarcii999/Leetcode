import java.util.*;

class Solution {
    public int mostBooked(int n, int[][] meetings) {

        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        int[] count = new int[n];

        PriorityQueue<Integer> available = new PriorityQueue<>();
        for (int i = 0; i < n; i++) available.add(i);

        // {endTime, room}
        PriorityQueue<long[]> used = new PriorityQueue<>(
            (a, b) -> a[0] == b[0]
                ? Long.compare(a[1], b[1])
                : Long.compare(a[0], b[0])
        );

        for (int[] m : meetings) {
            long start = m[0];
            long end = m[1];
            long duration = end - start;

            // Free rooms
            while (!used.isEmpty() && used.peek()[0] <= start) {
                available.add((int) used.poll()[1]);
            }

            if (!available.isEmpty()) {
                int room = available.poll();
                count[room]++;
                used.add(new long[]{end, room});
            } else {
                long[] top = used.poll();
                long newEnd = top[0] + duration;
                int room = (int) top[1];
                count[room]++;
                used.add(new long[]{newEnd, room});
            }
        }

        int best = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[best]) {
                best = i;
            }
        }
        return best;
    }
}
