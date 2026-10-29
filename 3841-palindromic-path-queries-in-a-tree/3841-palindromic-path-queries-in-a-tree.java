
import java.util.*;

class Solution {
    private int n, LOG;
    private List<Integer>[] adj;
    private int[][] up;
    private int[] depth, tin, tout, parent;
    private char[] chars;
    private BIT bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        parent = new int[n];
        chars = s.toCharArray();

        // Iterative DFS to avoid stack overflow
        int[] stack = new int[n];
        int[] order = new int[n];
        int[] size = new int[n];

        Arrays.fill(parent, -1);
        parent[0] = 0;

        int top = 0;
        int count = 0;
        stack[top++] = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = count;
            order[count++] = u;

            up[0][u] = parent[u];

            for (int j = 1; j < LOG; j++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }

            for (int v : adj[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        for (int i = 0; i < n; i++) {
            tout[i] = tin[i] + size[i] - 1;
        }

        bit = new BIT(n + 1);

        // Initialize character masks
        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');

            bit.update(tin[i], mask);
            bit.update(tout[i] + 1, mask);
        }

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] != c) {
                    int oldMask = 1 << (chars[u] - 'a');
                    int newMask = 1 << (c - 'a');
                    int diff = oldMask ^ newMask;

                    bit.update(tin[u], diff);
                    bit.update(tout[u] + 1, diff);

                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int mask = bit.query(tin[u])
                         ^ bit.query(tin[v])
                         ^ (1 << (chars[ancestor] - 'a'));

                // At most one character may have odd frequency
                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    private static class BIT {
        private final int[] tree;
        private final int n;

        BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int mask) {
            for (int i = index + 1; i <= n; i += i & -i) {
                tree[i] ^= mask;
            }
        }

        int query(int index) {
            int mask = 0;

            for (int i = index + 1; i > 0; i -= i & -i) {
                mask ^= tree[i];
            }

            return mask;
        }
    }
}
