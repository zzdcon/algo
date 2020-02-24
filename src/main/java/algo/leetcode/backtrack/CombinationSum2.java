package algo.leetcode.backtrack;//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]

//[[1, 2, 5], [1, 7], [1, 6, 1], [2, 6], [2, 1, 5], [7, 1]]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class CombinationSum2 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        dfs(new Stack<Integer>(), 0, candidates, target);
        return res;
    }

    private void dfs(Stack<Integer> stack, int index, int[] candidates,int target) {
        if (target == 0) {
            res.add(new ArrayList<>(stack));
        }
        HashSet<Integer> uniq = new HashSet<>();
        for (int i = index; i<candidates.length; i++) {
            if (uniq.contains(candidates[i])) {
                continue;
            }
            if (candidates[i] <= target) {
                stack.push(candidates[i]);
                dfs(stack, i+1, candidates, target-candidates[i]);
                stack.pop();
                uniq.add(candidates[i]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum2().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
