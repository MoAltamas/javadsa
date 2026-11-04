
import java.util.*;

class Solution {

    class Median {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        void addMap(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        void removeMap(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) map.remove(val);
            else map.put(val, count - 1);
        }

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                addMap(left, val);
                leftSize++;
                leftSum += val;
            } else {
                addMap(right, val);
                rightSize++;
                rightSum += val;
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                removeMap(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                removeMap(right, val);
                rightSize--;
                rightSum -= val;
            }
            balance();
        }

        void balance() {
            int target = (leftSize + rightSize + 1) / 2;

            while (leftSize > target) {
                int val = left.lastKey();
                removeMap(left, val);
                leftSize--;
                leftSum -= val;

                addMap(right, val);
                rightSize++;
                rightSum += val;
            }

            while (leftSize < target) {
                int val = right.firstKey();
                removeMap(right, val);
                rightSize--;
                rightSum -= val;

                addMap(left, val);
                leftSize++;
                leftSum += val;
            }
        }

        long cost() {
            long median = left.lastKey();

            return median * leftSize - leftSum
                 + rightSum - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Store the input midway as required.
        int[] maritovexi = nums;

        // Calculate the cost of every window of size x.
        long[] cost = new long[n - x + 1];

        Median median = new Median();

        for (int i = 0; i < x; i++) {
            median.add(maritovexi[i]);
        }

        cost[0] = median.cost();

        for (int i = 1; i <= n - x; i++) {
            median.remove(maritovexi[i - 1]);
            median.add(maritovexi[i + x - 1]);

            cost[i] = median.cost();
        }

        // DP: minimum cost to select k non-overlapping windows.
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];

        for (int t = 1; t <= k; t++) {
            Arrays.fill(dp[t], INF);

            for (int i = n - 1; i >= 0; i--) {
                // Option 1: Skip this index.
                dp[t][i] = dp[t][i + 1];

                // Option 2: Select window starting at i.
                if (i <= n - x) {
                    dp[t][i] = Math.min(
                        dp[t][i],
                        cost[i] + dp[t - 1][i + x]
                    );
                }
            }
        }

        return dp[k][0];
    }
}
