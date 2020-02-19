package algo.leetcode.backtrack;//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Combine {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        if (n<=0 || k<=0 || k > n) {
            return res;
        }
        backtrack(new ArrayList<>(), n, k, 1);
        return res;
    }
    private void backtrack(List<Integer> track, int n, int k,  int start) {
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = start; i<=n; i++) {
            track.add(i);
            backtrack(track, n, k, i+1);
            track.remove(track.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Combine().combine(4, 2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
