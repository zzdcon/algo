package algo.leetcode.tree;
//将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
//
// 示例: 
//
// 给定有序数组: [-10,-3,0,5,9],
//
//一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        List<Integer> list = IntStream.of(nums).boxed().collect(Collectors.toList());
        return buildTreeNode(list);
    }
    private TreeNode buildTreeNode(List<Integer> list) {
        if (list.isEmpty()) {
            return null;
        }
        int rootIndex = list.size() / 2;
        TreeNode treeNode = new TreeNode(list.get(rootIndex));
        treeNode.left = buildTreeNode(list.subList(0, rootIndex));
        treeNode.right = buildTreeNode(list.subList(rootIndex+1, list.size()));
        return treeNode;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new SortedArrayToBST().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(treeNode);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
