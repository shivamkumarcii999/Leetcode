import java.util.*;

class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 0;

        // Step 1: add 1 to the last digit
        digits[n - 1] += 1;

        // Step 2: handle carry from right to left
        for (int i = n - 1; i >= 0; i--) {
            digits[i] += carry;
            carry = digits[i] / 10;
            digits[i] %= 10;
        }

        // Step 3: if carry still exists, create new array
        if (carry > 0) {
            int[] result = new int[n + 1];
            result[0] = 1;
            for (int i = 0; i < n; i++) {
                result[i + 1] = digits[i];
            }
            return result;
        }

        return digits;
    }
}
