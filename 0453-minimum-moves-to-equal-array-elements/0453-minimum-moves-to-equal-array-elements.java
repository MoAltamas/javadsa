
class Solution {
    public int minMoves(int[] nums) {

        int min = nums[0];
        int sum = 0;

        // Find minimum element and calculate sum
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }

        // Calculate minimum moves
        return sum - nums.length * min;
    }
}
