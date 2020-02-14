package algo.leetcode.backtracing;//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> backtrack = backtrack(0, nums);
        backtrack.add(new ArrayList<>());
        return backtrack;
    }

    public List<List<Integer>> backtrack(int indexStart, int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (indexStart == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[indexStart]);
            ans.add(list);
            return ans;
        }
        int num = nums[indexStart];
        List<List<Integer>> backtrack = backtrack(indexStart + 1, nums);
        for (List<Integer> list : backtrack) {
            List<Integer> plus = new ArrayList<>(list);
            plus.add(num);
            ans.add(plus);
        }

        List<Integer> list = new ArrayList<>();
        list.add(num);
        ans.add(list);
        ans.addAll(backtrack);
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new Subsets().subsets(new int[] {1, 2, 3}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
