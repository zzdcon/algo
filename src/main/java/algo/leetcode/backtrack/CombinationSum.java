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
        backtrack(new ArrayList<>(), target,0,  candidates);
        return res;
    }

    private void backtrack(List<Integer> track, int target, int start, int[] nums) {
        if (target == 0) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i=start; i<nums.length; i++) {
            if (nums[i] <= target) {
                track.add(nums[i]);
                backtrack(track, target-nums[i], i, nums);
                track.remove(track.size()-1);
            }
        }

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates==null)return res;
        dfs(target,0,new Stack<Integer>(),candidates);
        return res;
    }
    //深度遍历
    private void dfs(int target, int index, Stack<Integer> pre, int[] candidates) {
        //等于零说明结果符合要求
        if (target==0){
            res.add(new ArrayList<>(pre));
            return;
        }
        //遍历，index为本分支上一节点的减数的下标
        for (int i=index;i<candidates.length;i++){
            //如果减数大于目标值，则差为负数，不符合结果
            if (candidates[i]<=target){
                pre.push(candidates[i]);
                //目标值减去元素值
                dfs(target-candidates[i],i,pre, candidates);
                //每次回溯将最后一次加入的元素删除
                pre.pop();
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[]{3, 2, 5}, 8));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
