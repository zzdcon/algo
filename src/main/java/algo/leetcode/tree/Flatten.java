package algo.leetcode.tree;//给定一个二叉树，原地将它展开为链表。
//
// 例如，给定二叉树 
//
//     1
//   / \
//  2   5
// / \   \
//3   4   6 
//
// 将其展开为： 
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6 
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
class Flatten {
    public void flatten(TreeNode root) {
        dfs(root);
    }

    /**
     * 返回展开后最底层的叶子节点
     * @param root
     * @return
     */
    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return root;         }
        TreeNode left = root.left;
        if (left == null) {
            return root.right == null ? root : root.right;
        }
        TreeNode right = root.right;
        root.right = left;
        TreeNode leftBottom = dfs(left);
        leftBottom.right = right;
        dfs(right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNodeHelper.constructNode(new Integer[]{3, 9, 20, null, null, 15, 7});
        new Flatten().flatten(treeNode);
        System.out.println(treeNode);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
