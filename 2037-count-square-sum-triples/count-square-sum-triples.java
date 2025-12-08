class Solution {
    public int countTriples(int N) {
        int count = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int k2 = i * i + j * j;
                int k = (int) Math.sqrt(k2);

                if (k <= N && k * k == k2) {
                    count++;
                }
            }
        }
        return count;
    }
}
