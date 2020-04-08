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


    public void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    /**
     * Morris 算法的思想是在节点和它的直接前驱之间设置一个临时的链接：predecessor.right = root，从该节点开始，找到它的直接前驱并验证是否存在链接。
     *
     * 如果没有链接，设置连接并走向左子树。
     * 如果有连接，断开连接并走向右子树。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree/solution/hui-fu-er-cha-sou-suo-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     */
    public void recoverTree2(TreeNode root) {
        // predecessor is a Morris predecessor.
        // In the 'loop' cases it could be equal to the node itself predecessor == root.
        // pred is a 'true' predecessor,
        // the previous node in the inorder traversal.
        TreeNode x = null, y = null, pred = null, predecessor = null;

        while (root != null) {
            // If there is a left child
            // then compute the predecessor.
            // If there is no link predecessor.right = root --> set it.
            // If there is a link predecessor.right = root --> break it.
            if (root.left != null) {
                // Predecessor node is one step left
                // and then right till you can.
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root)
                    predecessor = predecessor.right;

                // set link predecessor.right = root
                // and go to explore left subtree
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // break link predecessor.right = root
                // link is broken : time to change subtree and go right
                else {
                    // check for the swapped nodes
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) x = pred;
                    }
                    pred = root;

                    predecessor.right = null;
                    root = root.right;
                }
            }
            // If there is no left child
            // then just go right.
            else {
                // check for the swapped nodes
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) x = pred;
                }
                pred = root;

                root = root.right;
            }
        }
        swap(x, y);
    }

    public static void main(String[] args) {
        new RecoverTree().recoverTree(TreeNodeHelper.constructNode(new Integer[]{1,3,null,null,2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
