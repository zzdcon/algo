package algo.leetcode.tree;

import algo.dataStructure.TreeNode;

public class AVLBinaryTree {

    //给定一个二叉树，判断它是否是高度平衡的二叉树。
    //
    // 本题中，一棵高度平衡二叉树定义为：
    //
    //
    // 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
    public boolean isBalanced(TreeNode root) {
        int[] depth = {0};
        return isAVL(root, depth);
    }

    private boolean isAVL(TreeNode root, int[] depth) {
        if (root == null) {
            depth[0] = 1;
            return true;
        }

        int[] leftDepth = {0};
        int[]  rightDepth = {0};
        if (isAVL(root.left, leftDepth) && isAVL(root.right, rightDepth)) {
            if (leftDepth[0]-rightDepth[0] < 2 && leftDepth[0] - rightDepth[0] > -2) {
                depth[0] = Math.max(leftDepth[0], rightDepth[0]);
                return true;
            }
        }
        return false;
    }
}
