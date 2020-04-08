package algo.leetcode.dfs;

//二叉搜索树中的两个节点被错误地交换。
//
// 请在不改变其结构的情况下，恢复这棵树。 
//
// 示例 1: 
//
// 输入: [1,3,null,null,2]
//
//   1
//  /
// 3
//  \
//   2
//
//输出: [3,1,null,null,2]
//
//   3
//  /
// 1
//  \
//   2
// 
//
// 示例 2: 
//
// 输入: [3,1,4,null,null,2]
//
//  3
// / \
//1   4
//   /
//  2
//
//输出: [2,1,4,null,null,3]
//
//  2
// / \
//1   4
//   /
//  3 
//
// 进阶: 
//
// 
// 使用 O(n) 空间复杂度的解法很容易实现。 
// 你能想出一个只使用常数空间的解决方案吗？ 
// 
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;
import algo.leetcode.tree.TreeNodeHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class RecoverTree {
    TreeNode bigNode = null;
    TreeNode smallNode = null;
    TreeNode lastNode = null;
    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = bigNode.val;
        bigNode.val = smallNode.val;
        smallNode.val = temp;
    }

    /**
     * 中序遍历，对于二叉搜索树而言, 自然而然是排好序的，记录上一次的节点，第一次出现顺序相反的数值大的节点是有问题的节点，
     * 最后一个顺序相反的情况中数值小的节点时另一个有问题的节点
     * @param cur
     * @return
     */
    void inorder(TreeNode cur) {
        if (cur == null) return;
        inorder(cur.left);
        System.out.println(cur.val);
        if (lastNode != null) {
            if (cur.val < lastNode.val) {
                smallNode = cur;
                if (bigNode == null) bigNode = lastNode;
            }
        }
        lastNode = cur;
        inorder(cur.right);
    }

    public static void main(String[] args) {
        new RecoverTree().recoverTree(TreeNodeHelper.constructNode(new Integer[]{1,3,null,null,2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
