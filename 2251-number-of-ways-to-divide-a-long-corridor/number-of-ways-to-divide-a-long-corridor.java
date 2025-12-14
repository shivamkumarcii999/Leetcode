class Solution {
    public int numberOfWays(String corridor) {

        int MOD = 1_000_000_007;
        int INF = 1000000000;

        // If seats are not exactly divisible into pairs, return 0
        int seatCount = 0;
        for (char c : corridor.toCharArray()) {
            if (c == 'S') seatCount++;
        }
        if (seatCount == 0 || seatCount % 2 != 0) {
            return 0;
        }

        int currentSeats = 0;
        int currentPlants = -INF;
        long ways = 1;

        for (char c : corridor.toCharArray()) {

            if (c == 'S') {
                currentSeats++;

                if (currentSeats == 2) {
                    currentSeats = 0;
                    currentPlants = 0;
                } else {
                    if (currentPlants >= 0) {
                        ways = (ways * (currentPlants + 1)) % MOD;
                    }
                }

            } else { // c == 'P'
                currentPlants++;
            }
        }

        return (int)(ways % MOD);
    }
}
