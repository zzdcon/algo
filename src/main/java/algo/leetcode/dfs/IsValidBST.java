package algo.leetcode.dfs;//给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;

import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class IsValidBST {

    /**
     * 广度优先搜索，定位一个节点，深度遍历判断左分支节点全部小于root节点， 在判断所有右分支节点全部大于root节点
     * 判断完再分别判断该节点的左节点和右节点
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!dfs(root.left, true, root.val) || !dfs(root.right, false, root.val)) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    private boolean dfs(TreeNode node, boolean left, int rootValue) {
        if (node == null) {
            return true;
        }
        if (left) {
            if (node.val >= rootValue) {
                return false;
            }
        } else if (node.val <= rootValue) {
            return false;
        }
        return dfs(node.left, left, rootValue) && dfs(node.right, left, rootValue);
    }


    /**
     * 前序遍历，把一个节点值的范围传进去
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode node, long minMalue, long maxValue) {
        if (node == null) return true;
        if (node.val <= minMalue || node.val>= maxValue) {
            return false;
        }
        return dfs(node.left, minMalue, node.val) && dfs(node.right, node.val, maxValue);
    }


    /**
     * 中序遍历（通过栈实现）判断单调递增
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long lastVal = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= lastVal) return false;
            lastVal = root.val;
            root = root.right;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
