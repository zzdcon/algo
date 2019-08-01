package algo.leetcode.tree.traversal;//给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其自底向上的层次遍历为： 
//
// [
//  [15,7],
//  [9,20],
//  [3]
//]
// 
//

import algo.dataStructure.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class LevelOrderBottom {
    // 方法一: 使用链表，层级遍历，每层数据追加到链表尾部
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        LinkedList<List<Integer>> lists = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            lists.addFirst(list);
        }
        return lists;
    }

    // 方法二： 递归, 利用ArrayList是数组的特性，取第n层，确定没插入到数组头部，每次都会从
    // 第二个位置起整体拷贝
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        traverse(root, lists, 0);
        return lists;
    }

    public void traverse(TreeNode treeNode, List<List<Integer>> lists, int level) {
        if (treeNode == null) {
            return;
        }
        if (lists.size() <= level) {
            lists.add(0, new ArrayList<>());
        }
        // 倒数第level个位置
        lists.get(lists.size() - level - 1).add(treeNode.val);
        traverse(treeNode.left, lists, level + 1);
        traverse(treeNode.right, lists, level + 1);
    }

}