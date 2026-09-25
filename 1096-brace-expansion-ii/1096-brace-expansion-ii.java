class Solution {
    TreeSet<String> set = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(set);
    }

    void dfs(String s) {

        int close = s.indexOf('}');

        // No more braces
        if (close == -1) {
            set.add(s);
            return;
        }

        // Find matching opening brace
        int open = close - 1;
        while (s.charAt(open) != '{') {
            open--;
        }

        String left = s.substring(0, open);
        String right = s.substring(close + 1);

        // Split only at commas belonging to this brace level
        String inside = s.substring(open + 1, close);

        int start = 0;
        int level = 0;

        for (int i = 0; i <= inside.length(); i++) {

            if (i < inside.length()) {
                char ch = inside.charAt(i);

                if (ch == '{') level++;
                else if (ch == '}') level--;
            }

            if (i == inside.length() ||
                (inside.charAt(i) == ',' && level == 0)) {

                String part = inside.substring(start, i);

                dfs(left + part + right);

                start = i + 1;
            }
        }
    }
}