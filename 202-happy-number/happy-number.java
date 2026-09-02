class Solution {
    public boolean isHappy(int num) {
        while(num!=1 && num!=4) {
            int sum = 0;
            while(num!=0){
                int rem = num%10;
                sum += rem * rem;
                num/=10;
            }
            num = sum;
        }
        return num == 1;
    }
}