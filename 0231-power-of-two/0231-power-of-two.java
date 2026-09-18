class Solution {
    public boolean isPowerOfTwo(int n) {
        // A power of two must be strictly greater than 0, 
        // and clearing its lowest bit should result in 0.
        return n > 0 && (n & (n - 1)) == 0;
    }
}