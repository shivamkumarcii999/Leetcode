import java.util.*;

class Solution {

    private boolean checkSorted(List<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> doOperation(List<Integer> arr) {
        int n = arr.size();
        int bestIndex = 0;

        for (int i = 1; i < n - 1; i++) {
            int curr = arr.get(i) + arr.get(i + 1);
            int best = arr.get(bestIndex) + arr.get(bestIndex + 1);
            if (curr < best) {
                bestIndex = i;
            }
        }

        List<Integer> newArr = new ArrayList<>();

        for (int i = 0; i < bestIndex; i++) {
            newArr.add(arr.get(i));
        }

        newArr.add(arr.get(bestIndex) + arr.get(bestIndex + 1));

        for (int i = bestIndex + 2; i < n; i++) {
            newArr.add(arr.get(i));
        }

        return newArr;
    }

    // 🔧 FIXED METHOD SIGNATURE
    public int minimumPairRemoval(int[] nums) {

        // convert int[] → List<Integer>
        List<Integer> list = new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }

        int operations = 0;
        while (!checkSorted(list)) {
            list = doOperation(list);
            operations++;
        }

        return operations;
    }
}
