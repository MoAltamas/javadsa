
class Solution {
    public int lengthOfLastWord(String s) {

        // Remove extra spaces from the end
        s = s.trim();

        int count = 0;

        // Traverse string from right to left
        for (int i = s.length() - 1; i >= 0; i--) {

            if (s.charAt(i) != ' ') {
                count++;
            } else {
                break;
            }
        }

        return count;
    }
}
