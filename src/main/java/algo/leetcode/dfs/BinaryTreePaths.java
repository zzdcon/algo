package algo.leetcode.dfs;//给定一个二叉树，返回所有从根节点到叶子节点的路径。
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;
import algo.leetcode.tree.TreeNodeHelper;

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
class BinaryTreePaths {
    List<String> ans = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        dfs2(root, new StringBuilder());
        return ans;
    }

    /**
     * dfs1: 透传routine, 不需要返回，遍历到叶子节点，加入全局结果
     */
    public void dfs2(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            sb.append(node.val);
            ans.add(sb.toString());
        } else {
            sb.append(node.val).append("->");
        }

        dfs2(node.left, new StringBuilder().append(sb));
        dfs2(node.right, new StringBuilder().append(sb));
    }

    /**
     * dfs2: 需要根据left, right的返回再拼凑
     *
     * @param treeNode
     * @return
     */
    public List<List<Integer>> dfs(TreeNode treeNode) {
        if (treeNode == null) return new ArrayList<>();
        List<List<Integer>> left = dfs(treeNode.left);
        List<List<Integer>> right = dfs(treeNode.right);
        left.addAll(right);
        if (left.size() == 0) {
            List<Integer> list = new ArrayList<>();
            list.add(treeNode.val);
            left.add(list);
            return left;
        }
        for (List<Integer> list : left) {
            list.add(0, treeNode.val);
        }
        return left;
    }


    public static void main(String[] args) {
        TreeNode node = TreeNodeHelper.constructNode(new Integer[]{1, 2, 3, null, 5, null, null});
        System.out.println(new BinaryTreePaths().binaryTreePaths(node));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
