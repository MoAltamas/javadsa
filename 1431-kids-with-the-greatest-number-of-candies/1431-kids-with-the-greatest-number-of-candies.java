class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int n = candies.length;
        int maxCandies = 0;
        for (int i = 0; i < n; i++) {
            if (candies[i] > maxCandies) {
                maxCandies = candies[i];
            }
        }
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(candies[i] + extraCandies >= maxCandies);
        }

        return result;
    }
}