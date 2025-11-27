class Solution {

    // Check if t repeated k times is a subsequence of s
    private boolean isValid(String s, String t, int k) {
        int n = s.length(), m = t.length();
        int j = 0, count = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
                if (j == m) {
                    count++;
                    j = 0;
                    if (count == k) return true;
                }
            }
        }

        return false;
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // We only keep letters that appear at least k times
        List<Character> candidates = new ArrayList<>();

        for (char c = 'a'; c <= 'z'; c++) {
            if (freq[c - 'a'] >= k) {
                candidates.add(c);
            }
        }

        Collections.sort(candidates);  // lexicographically increasing

        Queue<String> queue = new LinkedList<>();
        queue.offer("");

        String best = "";

        while (!queue.isEmpty()) {
            String cur = queue.poll();

            for (char c : candidates) {
                String next = cur + c;

                if (isValid(s, next, k)) {
                    queue.offer(next);
                    // Keep lexicographically largest and longest
                    if (next.length() > best.length() ||
                       (next.length() == best.length() && next.compareTo(best) > 0)) {
                        best = next;
                    }
                }
            }
        }

        return best;
    }
}
