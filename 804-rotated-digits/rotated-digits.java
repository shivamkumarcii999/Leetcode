class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) count++;
        }
        
        return count;
    }
    
    private boolean isGood(int num) {
        boolean changed = false;
        
        while (num > 0) {
            int digit = num % 10;
            
            // Invalid digits — rotation produces nothing valid
            if (digit == 3 || digit == 4 || digit == 7) {
                return false;
            }
            
            // These digits change on rotation
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                changed = true;
            }
            
            num /= 10;
        }
        
        // Valid only if at least one digit changed
        return changed;
    }
}