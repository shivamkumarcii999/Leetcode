class Solution {

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {

        long left = 0;
        long right = (long)1e18;

        while (left < right) {

            long mid = left + (right - left) / 2;
            long totalHeight = 0;

            for (int t : workerTimes) {

                long low = 0, high = mountainHeight;

                while (low <= high) {

                    long k = (low + high) / 2;
                    long timeNeeded = (long)t * k * (k + 1) / 2;

                    if (timeNeeded <= mid) {
                        low = k + 1;
                    } else {
                        high = k - 1;
                    }
                }

                totalHeight += high;

                if (totalHeight >= mountainHeight)
                    break;
            }

            if (totalHeight >= mountainHeight)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}