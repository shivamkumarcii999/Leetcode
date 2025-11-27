class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        
        // Calculate number of groups
        int groups = (n + k - 1) / k;
        
        String[] result = new String[groups];
        int index = 0;
        
        for (int i = 0; i < groups; i++) {
            StringBuilder sb = new StringBuilder();
            
            // Add characters for current group
            for (int j = 0; j < k; j++) {
                if (index < n) {
                    sb.append(s.charAt(index));
                    index++;
                } else {
                    sb.append(fill);   // Add fill character if string ends
                }
            }
            
            result[i] = sb.toString();
        }
        
        return result;
    }
}
