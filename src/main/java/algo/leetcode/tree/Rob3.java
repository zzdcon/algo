package algo.leetcode.tree;


//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。 
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。 
//
// 示例 1: 
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7. 
//
// 示例 2: 
//
// 输入: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
// 
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;

import java.util.HashMap;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Rob3 {

    /**
     * 方法一： DFS
     *      执行耗时:1119 ms,击败了9.93% 的Java用户
     *      内存消耗:39.7 MB,击败了26.69% 的Java用户
     * @param root
     * @return
     */
    public int rob1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(rob(root, false), rob(root, true));
    }

    private int rob(TreeNode root, boolean selectRoot) {
        if (root == null) return 0;
        int ans;
        if (selectRoot) {
            //当前节点选择偷时，那么两个孩子节点就不能选择偷了
            ans = root.val + rob(root.left, false) + rob(root.right, false);
        } else {
            // 当前节点选择不偷时，两个孩子节点只需要拿最多的钱出来就行(两个孩子节点偷不偷没关系)
            return rob(root.left) + rob(root.right);
        }
        return ans;
    }

    /**
     * 方法二： 简化后的DFS
     *
     *  	执行耗时:875 ms,击败了20.12% 的Java用户
     * 		内存消耗:40 MB,击败了26.33% 的Java用户
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // root 不选中
        int ans1 = rob(root.left) + rob(root.right);
        // root 选中时，left/right不选择
        int ans2 =root.val +  (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        return Math.max(ans1, ans2);
    }

    /**
     * 返回每个节点的两个返回值，一个是当前节点选中时的结果，一个是当前节点不被选中的结果
     *  一次返回两个结果，减少一遍循环
     *
     * 			执行耗时:1 ms,击败了99.38% 的Java用户
     * 		    内存消耗:39.6 MB,击败了27.04% 的Java用户
     * @param root
     * @return
     */
    public int rob3(TreeNode root) {
        if (root == null) return 0;
        int[] dfs = dfs(root);
        return Math.max(dfs[0], dfs[1]);
    }

    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[2];
        }

        int[] res = new int[2];
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        //不选root
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //选root, 不选left, 不选right
        res[1] = root.val + left[0] + right[0];

        return res;
    }


    public static void main(String[] args) {
        System.out.println(new Rob3().rob(TreeNodeHelper.constructNode(new Integer[]{3,4,5,1,3,null, 1})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
