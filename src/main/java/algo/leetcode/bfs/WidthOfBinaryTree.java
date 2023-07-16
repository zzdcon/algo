package algo.leetcode.bfs;//给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
//
// 树的 最大宽度 是所有层中最大的 宽度 。 
//
// 
// 
// 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 
//null 节点，这些 null 节点也计入长度。 
// 
// 
//
// 题目数据保证答案将会在 32 位 带符号整数范围内。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,3,2,5,3,null,9]
//输出：4
//解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,3,2,5,null,null,9,6,null,7]
//输出：7
//解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
// 
//
// 示例 3： 
// 
// 
//输入：root = [1,3,2,5]
//输出：2
//解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目范围是 [1, 3000] 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 582 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class WidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 记录节点index
        Map<TreeNode, Integer> nodeMap = new HashMap<>() ;
        nodeMap.put(root, 1);
        // 记录结果
        int ans = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int start = nodeMap.get(queue.peek());
            while(size>0) {
                size--;
                TreeNode node = queue.poll();
                int index = nodeMap.get(node);
                if(node.left != null) {
                    queue.offer(node.left);
                    nodeMap.put(node.left, index*2);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                    nodeMap.put(node.right, index*2+1);
                }
                // 最后一层，计算当前宽度，更新最大宽度
                if(size == 0) {
                    ans = Math.max(ans, index - start + 1);
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
