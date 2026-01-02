import java.util.HashSet;

class Solution {
    public int repeatedNTimes(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            // If already present, this is the repeated element
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }

        // This line will never be reached due to problem constraints
        return -1;
    }
}
