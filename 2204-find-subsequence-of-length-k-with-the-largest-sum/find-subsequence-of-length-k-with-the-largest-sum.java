class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        // Min-heap storing (value, index)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]   // smallest value removed first
        );

        // Push elements, but keep only top k by value
        for (int i = 0; i < nums.length; i++) {
            pq.offer(new int[]{nums[i], i});
            if (pq.size() > k) {
                pq.poll();  // remove smallest
            }
        }

        // Collect selected indices
        List<int[]> picked = new ArrayList<>(pq);

        // Sort by index to maintain original order
        Collections.sort(picked, (a, b) -> a[1] - b[1]);

        // Build result
        int[] ans = new int[k];
        int idx = 0;
        for (int[] pair : picked) {
            ans[idx++] = pair[0];
        }

        return ans;
    }
}
