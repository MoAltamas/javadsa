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
    public void inorderHelper(TreeNode node,List<Integer>out){
        if(node==null){
            return;
        }
        inorderHelper(node.left,out);
        out.add(node.val);
        inorderHelper(node.right,out);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer>out =new ArrayList<>();
        if(root==null){
            return out;
        }
        inorderHelper(root,out);
        return out;
    }
}