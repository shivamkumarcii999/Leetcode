import java.util.*;

class Solution {

    // helper function
    private int f(int x) {
        for (int i = 1; i <= x; i++) {
            if ( (i | (i + 1)) == x ) {
                return i;
            }
        }
        return -1;
    }

    public int[] minBitwiseArray(List<Integer> nums) {
        int[] ans = new int[nums.size()];

        for (int i = 0; i < nums.size(); i++) {
            ans[i] = f(nums.get(i));
        }

        return ans;
    }
}
