package algo.leetcode.backtrack;//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [2,3,6,7], target = 7,
//所求解集为:
//[
//  [7],
//  [2,2,3]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,3,5], target = 8,
//所求解集为:
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//] 
// Related Topics 数组 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class CombinationSum {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        backtrack(new ArrayList<>(), target, 0, candidates);
        return res;
    }

    private void backtrack(List<Integer> track, int target, int start, int[] nums) {
        int sum=0;
        for (Integer num : track) {
            sum += num;
        }

        if (sum == target) {
            res.add(new ArrayList<>(track));
        } else if (sum > target) {
            return;
        }
        HashSet<Integer> uniq = new HashSet<>();

        for (int i=start; i<nums.length; i++) {
            if (track.size() >=1 && nums[i] < track.get(track.size()-1)) {
                continue;
            }
            track.add(nums[i]);
            backtrack(track, target, start, nums);
            track.remove(track.size()-1);
        }

    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[]{2, 3, 5}, 8));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
