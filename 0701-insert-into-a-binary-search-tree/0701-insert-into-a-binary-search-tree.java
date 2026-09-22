/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) {
            return node;
        }
        
        TreeNode pre = null, curr = root;
        
        while (curr != null) {
            if (val < curr.val) {
                pre = curr;
                curr = curr.left;
                if (curr == null) {
                    pre.left = node;
                    return root;
                }
            } else {
                pre = curr;
                curr = curr.right;
                if (curr == null) {
                    pre.right = node;
                    return root;
                }
            }
        }
        
        return root;
    }
}