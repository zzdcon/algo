package algo.leetcode.tree;//根据一棵树的中序遍历与后序遍历构造二叉树。
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 深度优先搜索 数组


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class BuildTreeWithPostIn {
    /**
     * 这类题目思路都是先找到root值，然后找到中序遍历的数据中root的位置，该位置前面数据属于左子树，后面数据属于右子树，然后通过递归生成树
     * root为前序遍历第一个数据，后序遍历的最后一个数据
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int il, int ir, int[] postorder, int pl, int pr) {
        if (inorder.length == 0 || il>ir) {
            return null;
        }
        // root的值为后序遍历的最后一个值
        int root = postorder[pr];
        TreeNode rootNode = new TreeNode(root);
        if (il == ir) {
            return rootNode;
        }
        int mid = il;
        while (inorder[mid] != root) {
            mid++;
        }
        rootNode.left = buildTree(inorder, il, mid - 1, postorder, pl, pl + mid - il - 1);
        rootNode.right = buildTree(inorder, mid + 1, ir, postorder, pl + mid - il, pr - 1);
        return rootNode;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new BuildTreeWithPostIn().buildTree(new int[]{2,1}, new int[]{2,1});
        TreeNode treeNode2 = new BuildTreeWithPostIn().buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        System.out.println(treeNode);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
