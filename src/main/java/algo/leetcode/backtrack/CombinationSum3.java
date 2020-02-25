package algo.leetcode.backtrack;//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
//
// 说明： 
//
// 
// 所有数字都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 
//
// 示例 2: 
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics 数组 回溯算法


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class CombinationSum3 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k<=0 || n<=0) {
            return res;
        }
        dfs(new ArrayList<Integer>(), k, n, 1);
        return res;
    }

    private void dfs(List<Integer> track, int k, int n, int index) {
        if (track.size() == k) {
            if (n == 0) {
                res.add(new ArrayList<>(track));
            }
            return;
        }

        if (n == 0) {
            return;
        }

        for (int i = index; i<=9; i++) {
            if (i <= n) {
                track.add(i);
                dfs(track, k, n-i, i+1);
                track.remove(track.size()-1);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new CombinationSum3().combinationSum3(3, 9));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
