class Solution {
    public int countSpecialIntegers(int[] nums) {
        int count = 0;
        int i, j, k;

        for (i = 0; i < nums.length; i++) {
            for (j = i + 1; j < nums.length; j++) {
                for (k = j + 1; k < nums.length; k++) {
                    if (nums[i] == nums[j] &&
                        nums[j] == nums[k] &&
                        j - i == k - j) {

                        int total = 0;
                        for (int x = 0; x < nums.length; x++) {
                            if (nums[x] == nums[i]) {
                                total++;
                            }
                        }

                        if (total == 3) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}