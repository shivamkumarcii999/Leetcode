class Solution {
    public String reverseVowels(String s) {
        char[] ch = s.toCharArray();
        int left = 0;
        int right = ch.length-1;
        while(left<right){
            while(!isVowel(ch[left]) && left<right){
                left++;
            }
                 while(!isVowel(ch[right]) && left<right){
                right--;
            }
                char temp=ch[left];
                ch[left]=ch[right];
                ch[right]=temp;
                left++;
                right--;

            }
        return  new String(ch);

    }
    public boolean isVowel(char ch){
        return ch=='a' || ch=='e'|| ch=='i' || ch=='o' || ch=='u' || ch=='A' || ch=='E'|| ch=='I' || ch=='O' || ch=='U';
    }
}