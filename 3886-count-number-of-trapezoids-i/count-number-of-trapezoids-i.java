import java.util.*;
import java.math.*;

class Solution {
    static final long MOD = (long)1e9 + 7;

    // Function to compute nC2
    long comb2(long n) {
        if (n < 2) return 0;
        return (n * (n - 1) / 2) % MOD;
    }

    public int countTrapezoids(int[][] points) {

        // Count how many points share each Y-coordinate
        HashMap<Integer, Long> ys = new HashMap<>();
        for (int[] p : points) {
            int y = p[1];
            ys.put(y, ys.getOrDefault(y, 0L) + 1);
        }

        long totalPairs = 0;

        // Calculate total combination of all vertical pairs across all Y-levels
        for (long count : ys.values()) {
            totalPairs = (totalPairs + comb2(count)) % MOD;
        }

        long ans = 0;

        // For each Y-level, subtract its own pairs to get combinations with other levels
        for (long count : ys.values()) {
            long cp = comb2(count);
            ans = (ans + (cp * ((totalPairs - cp + MOD) % MOD)) % MOD) % MOD;
        }

        // Multiply with inverse(2)
        long inv2 = BigInteger.valueOf(2).modInverse(BigInteger.valueOf(MOD)).longValue();

        ans = (ans * inv2) % MOD;

        return (int) ans;
    }
}
