import java.util.*;

class Solution {

    // Helper method similar to d(x) in Python
    private int[] d(int x) {
        int r = 2;          // 1 and x
        int t = 1 + x;
        int i = 2;

        while (i * i <= x) {
            if (x % i == 0) {
                r += 2;
                t += i;
                t += x / i;

                if (i * i == x) {
                    r -= 1;
                    t -= i;
                }
            }
            i++;
        }
        return new int[]{r, t};
    }

    public int sumFourDivisors(int[] nums) {
        int total = 0;

        for (int x : nums) {
            int[] result = d(x);
            int r = result[0];
            int t = result[1];

            if (r == 4) {
                total += t;
            }
        }
        return total;
    }
}
