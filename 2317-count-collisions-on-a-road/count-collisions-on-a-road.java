class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();

        // count total L's and R's
        int count = 0;
        for (char c : directions.toCharArray()) {
            if (c == 'L' || c == 'R') {
                count++;
            }
        }

        // remove leading L's (these never collide)
        for (int i = 0; i < n; i++) {
            if (directions.charAt(i) != 'L') break; // stop when we hit something that's not 'L'
            count--;
        }

        // remove trailing R's (these never collide)
        for (int i = n - 1; i >= 0; i--) {
            if (directions.charAt(i) != 'R') break; // stop when we hit something that's not 'R'
            count--;
        }

        return Math.max(0, count); // guard: collisions can't be negative
    }

    // quick test
    public static void main(String[] args) {
        Solution s = new Solution();
        String directions = "LLRLRLLSLRLLSLSSSS";
        System.out.println(s.countCollisions(directions)); // expected 10
    }
}
