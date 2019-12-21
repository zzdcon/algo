package algo.leetcode.tree;//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
//
// 示例 : 
//给定二叉树 
//
// 
//          1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树



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
class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return maxDepth(root.left) + maxDepth(root.right);
    }

    public int maxDepth(TreeNode treeNode) {
        if (treeNode.left == null && treeNode.right == null) {
            return 1;
        }
        if (treeNode.left == null) {
            return maxDepth(treeNode.right);
        } else if (treeNode.right == null) {
            return maxDepth(treeNode.left);
        }

        return Math.max(maxDepth(treeNode.left), maxDepth(treeNode.right));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
