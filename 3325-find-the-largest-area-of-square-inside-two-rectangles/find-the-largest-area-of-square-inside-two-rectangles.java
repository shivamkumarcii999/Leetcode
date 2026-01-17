class Solution {

    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int N = bottomLeft.length;
        long best = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                best = Math.max(best, squareArea(i, j, bottomLeft, topRight));
            }
        }
        return best;
    }

    private long squareArea(int i, int j, int[][] bottomLeft, int[][] topRight) {
        int ax = bottomLeft[i][0];
        int ay = bottomLeft[i][1];
        int bx = topRight[i][0];
        int by = topRight[i][1];

        int cx = bottomLeft[j][0];
        int cy = bottomLeft[j][1];
        int dx = topRight[j][0];
        int dy = topRight[j][1];

        int overlapX = overlap(ax, bx, cx, dx);
        int overlapY = overlap(ay, by, cy, dy);

        long side = Math.min(overlapX, overlapY);
        return side * side;
    }

    private int overlap(int a, int b, int c, int d) {
        return Math.max(Math.min(b, d) - Math.max(a, c), 0);
    }
}
