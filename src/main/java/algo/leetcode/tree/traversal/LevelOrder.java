package algo.leetcode.tree.traversal;//给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
//

import algo.dataStructure.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) {
 * val = x; } }
 */
class LevelOrder {
    // 方法一: 用队列装一层数据
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            int size = treeNodes.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = treeNodes.poll();
                levelList.add(poll.val);
                if (poll.left != null)
                    treeNodes.add(poll.left);
                if (poll.right != null)
                    treeNodes.add(poll.right);
            }
            lists.add(levelList);
        }
        return lists;
    }

    // 方法二： 递归, 利用ArrayList是数组的特性，取第n层
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        traverse(root, lists, 0);
        return lists;
    }

    public void traverse(TreeNode treeNode, List<List<Integer>> lists, int level) {
        if (treeNode == null) {
            return;
        }
        if (lists.size() < level + 1) {
            lists.add(new ArrayList<>());
        }
        List<Integer> list = lists.get(level);
        list.add(treeNode.val);
        if (treeNode.left != null) {
            traverse(treeNode.left, lists, level + 1);
        }
        if (treeNode.right != null) {
            traverse(treeNode.right, lists, level + 1);
        }
    }

}