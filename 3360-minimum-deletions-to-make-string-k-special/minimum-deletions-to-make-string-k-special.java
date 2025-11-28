class Solution {
    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        Arrays.sort(freq);   // Sort frequencies

        int minDel = Integer.MAX_VALUE;

        // Try each freq[i] as the minimum allowed frequency
        for (int i = 0; i < 26; i++) {
            int base = freq[i];
            int deletions = 0;

            for (int j = 0; j < 26; j++) {
                int f = freq[j];

                if (f == 0) continue;

                if (f < base) {
                    // Must delete all of this character
                    deletions += f;
                } else if (f > base + k) {
                    // Reduce to base + k
                    deletions += f - (base + k);
                }
            }

            minDel = Math.min(minDel, deletions);
        }

        return minDel;
    }
}
