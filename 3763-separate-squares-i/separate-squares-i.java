class Solution {
    public double separateSquares(int[][] squares) {

        double totalArea = 0;
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;

        for (int[] s : squares) {
            int y = s[1], l = s[2];
            totalArea += (double) l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        for (int i = 0; i < 60; i++) {
            double mid = (low + high) / 2;
            double below = 0;

            for (int[] s : squares) {
                double bottom = s[1];
                double top = s[1] + s[2];
                double l = s[2];

                if (mid <= bottom) {
                    continue;
                } else if (mid >= top) {
                    below += l * l;
                } else {
                    below += (mid - bottom) * l;
                }
            }

            if (below * 2 < totalArea)
                low = mid;
            else
                high = mid;
        }

        return low;
    }
}
