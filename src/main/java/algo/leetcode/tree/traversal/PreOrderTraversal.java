package algo.leetcode.tree.traversal;//给定一个二叉树，返回它的 前序 遍历。
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
//

import algo.dataStructure.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class PreOrderTraversal {
    // 1. 递归解法
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        list.addAll(preorderTraversal(root.left));
        list.addAll(preorderTraversal(root.right));
        return list;
    }

    // 2. 迭代解法
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            // 取列表的最后一个
            TreeNode poll = linkedList.pollLast();
            list.add(poll.val);
            // 先放右
            if (poll.right != null) {
                linkedList.add(poll.right);
            }
            // 再放左
            if (poll.left != null) {
                linkedList.add(poll.left);
            }
        }
        return list;
    }
}