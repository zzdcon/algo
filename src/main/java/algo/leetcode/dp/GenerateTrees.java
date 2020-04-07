package algo.leetcode.dp;

//给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
//
// 示例:
//
// 输入: 3
//输出:
//[
//  [1,null,3,2],
//  [3,2,null,1],
//  [3,1,null,null,2],
//  [2,1,3],
//  [1,null,2,null,3]
//]
//解释:
//以上的输出对应以下 5 种不同结构的二叉搜索树：
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
//
// Related Topics 树 动态规划


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class GenerateTrees {

    /**
     * DFS
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return Collections.emptyList();
        return genTree(1, n);
    }

    private List<TreeNode> genTree(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start>end) {
            list.add(null);
            return list;
        }
        for (int i=start; i<=end; i++) {
            List<TreeNode> left = genTree(start, i-1);
            List<TreeNode> right = genTree(i+1, end);
            for (TreeNode l : left) {
                for(TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left =l ;
                    root.right = r;
                    list.add(root);
                }
            }
        }
        return list;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
