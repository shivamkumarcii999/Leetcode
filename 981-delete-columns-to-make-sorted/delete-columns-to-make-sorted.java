class Solution {
    public int minDeletionSize(String[] strs) {
        int N = strs.length;
        int L = strs[0].length();
        int count = 0;

        for (int j = 0; j < L; j++) {
            boolean good = true;

            for (int i = 1; i < N; i++) {
                if (strs[i].charAt(j) < strs[i - 1].charAt(j)) {
                    good = false;
                    break;
                }
            }

            if (!good) {
                count++;
            }
        }
        return count;
    }
}
