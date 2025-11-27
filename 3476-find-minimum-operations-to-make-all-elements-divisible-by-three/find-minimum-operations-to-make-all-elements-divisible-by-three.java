class Solution {
    public int minimumOperations(int[] nums) {
        int total = 0;
        for(int i=0; i<nums.length; i++)
        {
            if(nums[i] % 3 != 0)
            {
                total += 1;
            }
        }
        return total;
    }
}