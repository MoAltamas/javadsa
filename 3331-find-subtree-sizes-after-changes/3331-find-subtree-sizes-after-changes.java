
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        List<Integer>[] path = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            path[i] = new ArrayList<>();
        }

        int[] ans = new int[n];

        // node, original parent, state
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, -1, 0});

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();

            int node = cur[0];
            int par = cur[1];
            int state = cur[2];

            int ch = s.charAt(node) - 'a';

            if (state == 0) {
                ans[node] = 1;
                path[ch].add(node);

                stack.push(new int[]{node, par, 1});

                for (int child : tree[node]) {
                    stack.push(new int[]{child, node, 0});
                }

            } else {
                int newParent = par;

                if (path[ch].size() > 1) {
                    newParent = path[ch].get(
                        path[ch].size() - 2
                    );
                }

                if (newParent != -1) {
                    ans[newParent] += ans[node];
                }

                path[ch].remove(path[ch].size() - 1);
            }
        }

        return ans;
    }
}
