package algo.leetcode.tree;

//给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//给定如下二叉树，以及目标和 sum = 22， 
//
//               5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
// 
//
// 返回: 
//
// [
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
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
class PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return Collections.emptyList();
        }
        sum -= root.val;
        List<List<Integer>> list = new ArrayList<>();
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                List<Integer> sub = new ArrayList<>();
                sub.add(root.val);
                list.add(sub);
            }
            return list;
        }
        List<List<Integer>> lists = pathSum(root.left, sum);
        List<List<Integer>> lists1 = pathSum(root.right, sum);
        for (List<Integer> l : lists) {
            l.add(0, root.val);
        }
        for (List<Integer> r : lists1) {
            r.add(0, root.val);
        }
        list.addAll(lists);
        list.addAll(lists1);
        return list;
    }

    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, ans, new ArrayList<>(), sum);
        return ans;
    }

    private void dfs(TreeNode root, List<List<Integer>> ans, List<Integer> routine, int sum) {
        if (root == null) {
            return;
        }
        routine.add(root.val);
        sum -= root.val;
        if (root.left==null && root.right == null) {
            if (sum == 0) {
                ans.add(routine);
            }
            return;
        }
        dfs(root.left, ans, new ArrayList<>(routine), sum);
        dfs(root.right, ans, new ArrayList<>(routine), sum);
        routine.remove(routine.size()-1);
    }


        public static void main(String[] args) {
        System.out.println(new PathSum().pathSum(TreeNodeHelper.constructNode(new Integer[]{5, 4, 8, 11, null, 13, 4, 7,2,5,1}), 22));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
