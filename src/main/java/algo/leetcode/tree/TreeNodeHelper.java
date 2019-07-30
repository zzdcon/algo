package algo.leetcode.tree;

import algo.dataStructure.TreeNode;

public class TreeNodeHelper {
    public static TreeNode constructNode(Integer[] input) {
        TreeNode root = new TreeNode(input[0]);
        setNode(input, root, 1);
        return root;
    }

    public static void setNode(Integer[] input, TreeNode root, int index) {
        if (index >= input.length) {
            return;
        }

        TreeNode left = null;
        TreeNode right = null;
        if (root != null) {
            Integer leftVal = input[index];
            Integer rightVal = index + 1 > input.length -1 ? null : input[index + 1];
            left = leftVal != null ? new TreeNode(leftVal) : null;
            right = rightVal != null ? new TreeNode(rightVal): null;
            root.left = left;
            root.right = right;
        }

        setNode(input, left, index + 2);
        setNode(input, right, index + 4);
    }

    public static void main(String[] args) {
        TreeNode treeNode = constructNode(new Integer[] { 3, 9, 20, null, null, 15, 7 });
        System.out.println(treeNode);
    }
}
