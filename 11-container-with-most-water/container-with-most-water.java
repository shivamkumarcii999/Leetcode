class Solution {
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {

            // Calculate width
            int width = right - left;

            // Height of container = smaller line
            int minHeight = Math.min(height[left], height[right]);

            // Calculate current water
            int currentWater = width * minHeight;

            // Update maximum
            maxWater = Math.max(maxWater, currentWater);

            // Move the smaller line
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}