class Solution {
    public int countOdds(int low, int high) {
        return f(high) - f(low - 1);
    }

    private int f(int x) {
        return (x + 1) / 2;
    }
}
