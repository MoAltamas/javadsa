class Solution {
    public boolean judgeSquareSum(int c) {
        for (int a = 0; (long) a * a <= c; a++) {
            int remaining = c - a * a;
            int b = (int) Math.sqrt(remaining);

            if ((long) b * b == remaining) {
                return true;
            }
        }

        return false;
    }
}