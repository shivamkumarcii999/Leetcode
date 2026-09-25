class Solution {
    public boolean isPalindrome(String s) {

        int left = 0;
        String res = "";
        for(int i =0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                res+=ch;
            }}
            res = res.toLowerCase();
            int right = res.length()-1;
        while(left<right){
            if(res.charAt(left)!=res.charAt(right)){
                return false;

            }
            left++;
            right--;
        }
        return true;
    }
}