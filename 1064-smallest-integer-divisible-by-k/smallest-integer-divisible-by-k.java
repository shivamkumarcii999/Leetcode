class Solution {
    public int smallestRepunitDivByK(int k) {
        int count = 1;
        int current = 1;
        HashSet<Integer> used = new HashSet<>();

        while (current % k != 0) {
            // shift and add a 1 --> 1 becomes 1
            current *= 10;
            current += 1;
            count += 1;
            current %= k;
            
            if (used.contains(current)) {
                return -1;
            }
            
            used.add(current);
        }
        return count;

    }           
}