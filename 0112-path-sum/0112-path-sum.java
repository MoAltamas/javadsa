class Solution {
    private boolean result;

    public void preorderHelper(TreeNode node, int targetSum, int sum) {
        if (node == null) {
            return;
        }
    
        if (result) {
            return;
        }
        sum += node.val;

        if (node.left == null && node.right == null) {
            if (sum == targetSum) {
                result = true;
            }
            return;
        }
        preorderHelper(node.left, targetSum, sum);
        preorderHelper(node.right, targetSum, sum);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        result = false;
        if (root == null) {
            return false;
        }
        preorderHelper(root, targetSum, 0);
        return result;
    }
}