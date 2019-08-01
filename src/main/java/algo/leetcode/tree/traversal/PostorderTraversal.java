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

    // 方法二： 前序遍历，通过双向链表实现，始终在头结点插入数据
    public List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        LinkedList<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode pop = stack.pop();
            list.addFirst(pop.val);
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        return list;
    }

    // 方法三 双栈模式
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traverse(root, list);
        return list;
    }

    public void traverse(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop != null) {
                stack2.push(pop.val);
                stack.push(pop.left);
                stack.push(pop.right);
            }

        }

        while (!stack2.isEmpty()) {
            list.add(stack2.pop());
        }
    }
}