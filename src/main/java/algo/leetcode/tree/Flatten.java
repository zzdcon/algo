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

import java.util.Stack;

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
    /**
     * 方法一： 递归
     * 将左子树插入到右子树的地方
     * 将原来的右子树接到左子树的最右边节点
     * 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left == null) {
            flatten(root.right);
            return;
        }
        TreeNode cur = root.left;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = root.right;
        root.right = root.left;
        root.left = null;
        flatten(root.right);
    }

    /**
     * 前序遍历，通过堆栈实现
     * @param root
     */
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (pre != null) {
                pre.right = temp;
                pre.left = null;
            }
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
            pre = temp;
        }
    }



    public static void main(String[] args) {
        TreeNode treeNode = TreeNodeHelper.constructNode(new Integer[]{3, 9, 20, null, null, 15, 7});
        new Flatten().flatten2(treeNode);
        System.out.println(treeNode);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
