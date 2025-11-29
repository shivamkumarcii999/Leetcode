import java.util.*;

class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);

        int count = 1;  // At least one subsequence needed
        int start = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - start > k) {
                count++;
                start = nums[i];  // Reset starting value for new subsequence
            }
        }

        return count;
    }
}
