import java.util.*;

class Solution {

    public int maximizeSquareHoleArea(int R, int C, int[] hBars, int[] vBars) {

        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int h = cstreak(hBars);
        int w = cstreak(vBars);

        int side = Math.min(h, w) + 1;
        return side * side;
    }

    private int cstreak(int[] bars) {
        int streak = 0;
        int best = 0;

        int prev = Integer.MIN_VALUE;

        for (int b : bars) {
            if (b == prev + 1) {
                streak++;
            } else {
                streak = 1;
            }
            prev = b;
            best = Math.max(best, streak);
        }

        return best;
    }
}
