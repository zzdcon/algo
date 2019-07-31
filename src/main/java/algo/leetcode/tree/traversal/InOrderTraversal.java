package algo.leetcode.tree.traversal;//给定一个二叉树，返回它的中序 遍历。
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
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
//

import algo.dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class InOrderTraversal {
    // 方法一： 递归解法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.addAll(inorderTraversal(root.left));
            list.add(root.val);
            list.addAll(inorderTraversal(root.right));
        }
        return list;
    }

    // 方法二： 基于栈的遍历
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.empty()) {
           if (cur != null) {
               stack.push(cur);
               cur = cur.left;
           } else {
               cur = stack.pop();
               list.add(cur.val);
               cur = cur.right;
           }
        }
        return list;
    }
}