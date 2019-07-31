package algo.leetcode.tree.traversal;//给定一个二叉树，返回它的 后序 遍历。 
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
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
//

import algo.dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TransferQueue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class PostorderTraversal {
    // 方法一： 递归
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.addAll(postorderTraversal(root.left));
            list.addAll(postorderTraversal(root.right));
            list.add(root.val);
        }
        return list;
    }

    // 方法二： 通过栈
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()){
            if (cur != null) {
                stack.push(cur);
                if (cur.right != null)
                    stack.push(cur.right);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    list.add(cur.val);
                }
            }
        }
        return list;
    }
}