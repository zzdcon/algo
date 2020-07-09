package algo.leetcode.tree;//给出一个完全二叉树，求出该树的节点个数。
//
// 说明： 
//
// 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为
//第 h 层，则该层包含 1~ 2h 个节点。 
//
// 示例: 
//
// 输入: 
//    1
//   / \
//  2   3
// / \  /
//4  5 6
//
//输出: 6 
// Related Topics 树 二分查找


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
class CountNodes {

    /**
     * 方法一： 利用完全二叉树的特点，特殊的二分法
     *
     * 特别注意运算符的优先级顺序
     * 基本的优先级需要记住：
     * 指针最优，单目运算优于双目运算。如正负号。
     * 先算术运算，后移位运算，最后位运算。请特别注意：1 << 3 + 2 & 7等价于 (1 << (3 + 2))&7.
     * 逻辑运算最后结合。
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = findDepth(root.left);
        int right = findDepth(root.right);
        if (left == right)
            return (1<<left) + countNodes(root.right) ;
        else return countNodes(root.left) + (1<< right);
    }

//    public int countNodes(TreeNode root) {
//        /**
//         完全二叉树的高度可以直接通过不断地访问左子树就可以获取
//         判断左右子树的高度:
//         如果相等说明左子树是满二叉树, 然后进一步判断右子树的节点数(最后一层最后出现的节点必然在右子树中)
//         如果不等说明右子树是深度小于左子树的满二叉树, 然后进一步判断左子树的节点数(最后一层最后出现的节点必然在左子树中)
//         **/
//        if (root==null) return 0;
//        int ld = getDepth(root.left);
//        int rd = getDepth(root.right);
//        if(ld == rd)
//            return (1 << ld) + countNodes(root.right); // 1(根节点) + (1 << ld)-1(左完全左子树节点数) + 右子树节点数量
//        else return (1 << rd) + countNodes(root.left);  // 1(根节点) + (1 << rd)-1(右完全右子树节点数) + 左子树节点数量
//
//    }

    public int findDepth(TreeNode root) {
        if (root == null) return 0;
        return findDepth(root.left) + 1;
    }

    private int getDepth(TreeNode r) {
        int depth = 0;
        while(r != null) {
            depth++;
            r = r.left;
        }
        return depth;
    }

    /**
     * 方法二： 暴力递归法
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
