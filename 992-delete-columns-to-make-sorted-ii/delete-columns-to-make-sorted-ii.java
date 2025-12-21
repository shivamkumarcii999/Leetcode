class Solution {
    public int minDeletionSize(String[] strs) {
        int N = strs.length;
        int L = strs[0].length();
        int count = 0;

        // Stores prefixes formed so far
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = "";
        }

        for (int j = 0; j < L; j++) {
            boolean good = true;

            // Check if keeping this column breaks sorted order
            for (int i = 0; i < N - 1; i++) {
                String s1 = words[i] + strs[i].charAt(j);
                String s2 = words[i + 1] + strs[i + 1].charAt(j);

                if (s1.compareTo(s2) > 0) {
                    good = false;
                    break;
                }
            }

            if (good) {
                // Keep the column → update prefixes
                for (int i = 0; i < N; i++) {
                    words[i] += strs[i].charAt(j);
                }
            } else {
                // Delete the column
                count++;
            }
        }

        return count;
    }
}
