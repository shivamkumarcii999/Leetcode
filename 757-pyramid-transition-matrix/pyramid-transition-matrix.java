import java.util.*;

class Solution {

    private Map<String, List<Character>> lookup;
    private List<char[]> levels;
    private int N;

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        N = bottom.length();

        // Build lookup table
        lookup = new HashMap<>();
        for (String s : allowed) {
            String key = s.substring(0, 2);
            char val = s.charAt(2);
            lookup.computeIfAbsent(key, k -> new ArrayList<>()).add(val);
        }

        // Initialize levels
        levels = new ArrayList<>();
        levels.add(bottom.toCharArray());

        while (levels.size() < N) {
            levels.add(new char[levels.get(levels.size() - 1).length - 1]);
        }

        return dfs(1, 0);
    }

    private boolean dfs(int level, int col) {
        if (level == N) {
            return true;
        }

        if (col == levels.get(level).length) {
            return dfs(level + 1, 0);
        }

        char[] prev = levels.get(level - 1);
        String key = "" + prev[col] + prev[col + 1];

        if (!lookup.containsKey(key)) {
            return false;
        }

        for (char c : lookup.get(key)) {
            levels.get(level)[col] = c;
            if (dfs(level, col + 1)) {
                return true;
            }
        }

        return false;
    }
}
