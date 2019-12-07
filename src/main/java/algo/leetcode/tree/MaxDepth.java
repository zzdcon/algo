package algo.leetcode.tree;//给定一个二叉树，找出其最大深度。
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索



//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        if (root.left == null && root.right != null ) {
            return 1 + maxDepth(root.right);
        }

        if (root.right == null && root.left != null) {
            return 1 + maxDepth(root.left);
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right));

    }
}
//leetcode submit region end(Prohibit modification and deletion)
