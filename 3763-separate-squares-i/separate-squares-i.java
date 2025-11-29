class Solution {
    public double separateSquares(int[][] squares) {

        double left = 0, right = 1000000000;  // safe search range

        for (int i = 0; i < 60; i++) { // binary search precision
            double mid = (left + right) / 2.0;

            double above = 0.0, below = 0.0;

            for (int[] sq : squares) {
                double x = sq[0];
                double y = sq[1];
                double s = sq[2];

                double top = y + s;

                if (top <= mid) {
                    // whole square is below line
                    below += s * s;
                } 
                else if (y >= mid) {
                    // whole square is above line
                    above += s * s;
                } 
                else {
                    // square is split
                    double upperPart = top - mid;     // height above line
                    double lowerPart = mid - y;      // height below line

                    above += upperPart * s;
                    below += lowerPart * s;
                }
            }

            // We need equal areas
            if (above > below)
                left = mid;
            else
                right = mid;
        }

        return left;   // or right, both converge
    }
}
