package algo.leetcode.tree;//根据一棵树的前序遍历与中序遍历构造二叉树。
//
// 注意:
//你可以假设树中没有重复的元素。
//
// 例如，给出
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7]
//
// 返回如下的二叉树：
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// Related Topics 树 深度优先搜索 数组


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.TreeNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BuildTree {
    /**
     例如在例题中：

     前序遍历 preorder = [3,9,20,15,7]

     中序遍历 inorder = [9,3,15,20,7]

     preorder 的第一个元素 3 是整棵树的根节点。inorder 中 3 的左侧 [9] 是树的左子树，右侧 [15, 20, 7] 构成了树的右子树。

     所以构建二叉树的问题本质上就是：

     找到各个子树的根节点 root
     构建该根节点的左子树
     构建该根节点的右子树
     整个过程我们可以用递归来完成。

     作者：jalan
     链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/python-di-gui-xiang-jie-by-jalan/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> pre = IntStream.of(preorder).boxed().collect(Collectors.toList());
        List<Integer> in = IntStream.of(inorder).boxed().collect(Collectors.toList());
        return buildTree(pre, in);
    }

    private TreeNode buildTree(List<Integer> preorder, List<Integer> inorder) {
        if (preorder.size() == 0) {
            return null;
        }
        // 前序遍历第一个值为root
        TreeNode root = new TreeNode(preorder.get(0));
        // 找到root在中序遍历的位置，左边为左子树，右边为右子树
        int mid = 0;
        while (inorder.get(mid) != root.val) {
            mid++;
        }
        root.left = buildTree(preorder.subList(1, mid+1), inorder.subList(0, mid));
        root.right = buildTree(preorder.subList(mid+1, preorder.size()), inorder.subList(mid+1, inorder.size()));
        return root;
    }

    /**
     * 方法二： 用Arrays.copyOfRange方法
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if(preorder.length==0) return null;
        int val = preorder[0], i=0;
        TreeNode node = new TreeNode(val);
        while(inorder[i]!=val) i++;
        node.left = buildTree(Arrays.copyOfRange(preorder,1,i+1),Arrays.copyOfRange(inorder,0,i));
        node.right = buildTree(Arrays.copyOfRange(preorder,i+1,preorder.length),Arrays.copyOfRange(inorder,i+1,inorder.length));
        return node;
    }


    public static void main(String[] args) {
        TreeNode treeNode = new BuildTree().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(treeNode);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
