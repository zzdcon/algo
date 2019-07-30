package algo.leetcode.tree;

import algo.dataStructure.TreeNode;

import java.util.*;

import static algo.leetcode.tree.TreeNodeHelper.constructNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LevelOrderTraversal {

    /**
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 解题要点：队列法，遍历每一层，将每层数据从头部插入
    */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        LinkedList<List<Integer>> lists = new LinkedList<>();
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        while(!treeNodes.isEmpty()) {
            int size = treeNodes.size();
            List<Integer> list = new ArrayList<>();
            for (int i=0; i<size; i++) {
                TreeNode treeNode = treeNodes.poll();
                list.add(treeNode.val);
                if (treeNode.left != null) {
                    treeNodes.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    treeNodes.add(treeNode.right);
                }
            }
            lists.addFirst(list);
        }
        return lists;
    }

    /**
     *   给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     *   解题要点： 队列法
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> nodeList = new LinkedList();
        nodeList.add(root);
        while (!nodeList.isEmpty()) {
            List<Integer> leverList = new ArrayList<>();
            int size = nodeList.size();
            for(int i = 0; i < size; i++) {
                TreeNode treeNode = nodeList.poll();
                leverList.add(treeNode.val);
                if (treeNode.left != null) {
                    nodeList.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    nodeList.add(treeNode.right);
                }
            }
            list.add(leverList);
        }
        return list;
    }

    /**
     * 递归法，根据深度，添加到对应的层数； 增加helper方法，进行递归
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        helper(root, 1, lists);
        return lists;
    }

    public void helper(TreeNode root, int depth, List<List<Integer>> arrays) {
        if (root == null) {
            return;
        }
        if (depth>arrays.size()) {
            arrays.add(new ArrayList());
        }

        List list = arrays.get(depth - 1);
        list.add(root.val);
        helper(root.left, depth+1, arrays);
        helper(root.right, depth+1, arrays);

    }

    //给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> arrays = new ArrayList<>();
        helper2(root, 1, arrays);
        return arrays;
    }

    public void helper2(TreeNode root, int depth, List<List<Integer>> arrays) {
        if (root == null) {
            return;
        }

        if (depth > arrays.size()) {
            arrays.add(new ArrayList<>());
        }

        if (depth%2 == 1) {
            arrays.get(depth-1).add(root.val);
        } else {
            arrays.get(depth-1).add(0, root.val);
        }

        helper2(root.left, depth+1, arrays);
        helper2(root.right, depth+1, arrays);
    }

    public static void main(String[] args) {
        TreeNode treeNode = constructNode(new Integer[] { 3, 9, 20, null, null, 15, 7 });
        System.out.println(new LevelOrderTraversal().levelOrder(treeNode));
    }
}