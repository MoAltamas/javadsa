import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, n);

        // Step 1: Record leftmost and rightmost indices for each character
        for (int i = 0; i < n; ++i) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = i;
        }

        List<String> res = new ArrayList<>();
        int r = -1;

        // Step 2: Validate and find the best intervals greedily
        for (int i = 0; i < n; ++i) {
            if (i != left[s.charAt(i) - 'a']) continue;
            
            int newR = right[s.charAt(i) - 'a'];
            boolean valid = true;
            
            for (int j = i; j <= newR; ++j) {
                if (left[s.charAt(j) - 'a'] < i) {
                    valid = false;
                    break;
                }
                newR = Math.max(newR, right[s.charAt(j) - 'a']);
            }
            
            if (valid) {
                if (i > r) {
                    res.add("");
                }
                res.set(res.size() - 1, s.substring(i, newR + 1));
                r = newR;
            }
        }
        return res;
    }
}