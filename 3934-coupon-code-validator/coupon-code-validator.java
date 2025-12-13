import java.util.*;

class Solution {

    public List<String> validateCoupons(String[] code,
                                        String[] businessLine,
                                        boolean[] isActive) {

        // Business line priority
        Map<String, Integer> priority = new HashMap<>();
        priority.put("electronics", 0);
        priority.put("grocery", 1);
        priority.put("pharmacy", 2);
        priority.put("restaurant", 3);

        List<Pair> validCoupons = new ArrayList<>();

        for (int i = 0; i < code.length; i++) {

            // Condition 1: active
            if (!isActive[i]) continue;

            // Condition 2: valid business line
            if (!priority.containsKey(businessLine[i])) continue;

            // Condition 3: valid code
            if (code[i] == null || code[i].isEmpty()) continue;
            if (!code[i].matches("[A-Za-z0-9_]+")) continue;

            validCoupons.add(new Pair(
                    businessLine[i],
                    code[i],
                    priority.get(businessLine[i])
            ));
        }

        // Sorting as required
        Collections.sort(validCoupons, (a, b) -> {
            if (a.rank != b.rank)
                return a.rank - b.rank;
            return a.code.compareTo(b.code);
        });

        // Extract only coupon codes
        List<String> result = new ArrayList<>();
        for (Pair p : validCoupons) {
            result.add(p.code);
        }

        return result;
    }

    // Helper class
    static class Pair {
        String business;
        String code;
        int rank;

        Pair(String business, String code, int rank) {
            this.business = business;
            this.code = code;
            this.rank = rank;
        }
    }
}
