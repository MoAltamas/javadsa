class Solution {
    public long maxAlternatingSum(int[] nums) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);

        int i = 0,
                j = nums.length - 1;

        while (i <= j) {
            if (i == j)
                sum += nums[j];
            else {
                sum += nums[j] - nums[i];

            }
            i++;
            j--;

        }
        return sum;

    }
}