import java.util.List;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int target = nums.get(i);
            
            // Since nums[i] is prime, 2 is the only even case.
            // No x exists such that x OR (x+1) == 2.
            if (target == 2) {
                ans[i] = -1;
            } else {
                // Find the first '0' bit from the right.
                // For odd primes, we flip the 1-bit just before the first 0-bit.
                for (int bit = 0; bit < 31; bit++) {
                    if (((target >> bit) & 1) == 0) {
                        // bit-1 is the position of the last '1' in the trailing block
                        ans[i] = target ^ (1 << (bit - 1));
                        break;
                    }
                }
            }
        }
        
        return ans;
    }
}