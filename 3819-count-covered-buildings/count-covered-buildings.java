import java.util.*;

public class Solution {
    /**
     * Count buildings that are "covered" (have at least one building
     * strictly above, below, left and right).
     *
     * Signature matches driver: countCoveredBuildings(int N, int[][] buildings)
     */
    public int countCoveredBuildings(int N, int[][] buildings) {
        // in case driver gives wrong N, fall back to actual length
        if (buildings != null && N != buildings.length) {
            N = buildings.length;
        }

        // map x -> list of (y, index)
        Map<Integer, List<int[]>> xs = new HashMap<>();
        // map y -> list of (x, index)
        Map<Integer, List<int[]>> ys = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int x = buildings[i][0];
            int y = buildings[i][1];
            xs.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[]{y, i});
            ys.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{x, i});
        }

        boolean[] up = new boolean[N];
        boolean[] down = new boolean[N];
        boolean[] left = new boolean[N];
        boolean[] right = new boolean[N];

        // For each vertical line (same x), sort by y and mark left/right
        for (List<int[]> list : xs.values()) {
            list.sort(Comparator.comparingInt(a -> a[0])); // sort by y
            for (int i = 1; i < list.size(); i++) {
                left[list.get(i)[1]] = true;
            }
            for (int i = 0; i < list.size() - 1; i++) {
                right[list.get(i)[1]] = true;
            }
        }

        // For each horizontal line (same y), sort by x and mark up/down
        for (List<int[]> list : ys.values()) {
            list.sort(Comparator.comparingInt(a -> a[0])); // sort by x
            for (int i = 1; i < list.size(); i++) {
                up[list.get(i)[1]] = true;
            }
            for (int i = 0; i < list.size() - 1; i++) {
                down[list.get(i)[1]] = true;
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (up[i] && down[i] && left[i] && right[i]) count++;
        }
        return count;
    }

    // optional overload: accept only buildings (if you prefer)
    public int countCoveredBuildings(int[][] buildings) {
        return countCoveredBuildings(buildings.length, buildings);
    }

    // Example main (not used by the online judge driver)
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] buildings = {
            {1, 1},
            {2, 1},
            {1, 2},
            {2, 2},
            {3, 2}
        };
        System.out.println(s.countCoveredBuildings(buildings.length, buildings));
    }
}
