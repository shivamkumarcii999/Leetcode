class Solution {

    public int minTimeToVisitAllPoints(int[][] points) {
        int total = 0;

        for (int i = 0; i < points.length - 1; i++) {
            int ax = points[i][0];
            int ay = points[i][1];
            int bx = points[i + 1][0];
            int by = points[i + 1][1];

            total += distance(ax, ay, bx, by);
        }

        return total;
    }

    private int distance(int ax, int ay, int bx, int by) {
        return Math.max(Math.abs(ax - bx), Math.abs(ay - by));
    }
}
