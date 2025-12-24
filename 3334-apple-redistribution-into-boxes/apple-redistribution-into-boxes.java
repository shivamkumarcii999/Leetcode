import java.util.Arrays;

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {

        // Step 1: Calculate total apples
        int apples = 0;
        for (int a : apple) {
            apples += a;
        }

        // Step 2: Sort capacity in descending order
        Arrays.sort(capacity);
        int left = 0, right = capacity.length - 1;
        while (left < right) {
            int temp = capacity[left];
            capacity[left] = capacity[right];
            capacity[right] = temp;
            left++;
            right--;
        }

        // Step 3: Use boxes greedily
        int cindex = 0;
        while (apples > 0) {
            apples -= capacity[cindex];
            cindex++;
        }

        return cindex;
    }
}
