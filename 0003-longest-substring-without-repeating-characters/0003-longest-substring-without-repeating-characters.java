class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int i = 0,
                j = 0;
        Set<Character> set = new HashSet<>();
        while (j < s.length()) {
            char c = s.charAt(j);
            while (set.contains(c)) {
                set.remove(s.charAt(i));
                i = i + 1;
            }
            set.add(c);
            max = Math.max(max, j - i + 1);
            j = j + 1;
        }
        return max;
    }
}