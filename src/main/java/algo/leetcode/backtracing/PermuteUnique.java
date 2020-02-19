package algo.leetcode.backtracing;//给定一个可包含重复数字的序列，返回所有不重复的全排列。
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class PermuteUnique {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        backtrack(list, 0);
        return res;
    }


    private void backtrack(List<Integer> track, int start) {
        if (start == track.size()) {
            res.add(new ArrayList(track));
            return;
        }

        HashSet<Integer> used = new HashSet<>();
        for (int i=start; i<track.size(); i++) {
            if (used.contains(track.get(i))) {
                continue;
            }
            Collections.swap(track, start, i);
            backtrack(track, start+1);
            Collections.swap(track, start, i);
            used.add(track.get(i));
        }
    }



    public static void main(String[] args) {
        System.out.println(new PermuteUnique().permuteUnique(new int[]{1, 2, 3}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
