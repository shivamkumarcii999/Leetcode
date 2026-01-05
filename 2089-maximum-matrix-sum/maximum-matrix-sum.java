class Solution {
    public long maxMatrixSum(int[][] matrix) {

        long sum = 0;               // FIX 1: use long
        int minAbs = Integer.MAX_VALUE;
        int negativeCount = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                int val = matrix[i][j];

                if (val < 0) negativeCount++;

                int absVal = Math.abs(val);
                sum += absVal;     // sum of absolute values

                minAbs = Math.min(minAbs, absVal);
            }
        }

        // FIX 2: if negatives are odd, subtract twice smallest abs
        if (negativeCount % 2 != 0) {
            sum -= 2L * minAbs;
        }

        return sum;
    }
}
