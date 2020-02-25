package algo.leetcode.backtrack;//给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
//
// 示例: 
//
// 
//nums = [1, 2, 3]
//target = 4
//
//所有可能的组合为：
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//
//请注意，顺序不同的序列被视作不同的组合。
//
//因此输出为 7。
// 
//
// 进阶： 
//如果给定的数组中含有负数会怎么样？ 
//问题会产生什么变化？ 
//我们需要在题目中添加什么限制来允许负数的出现？ 
//
// 致谢： 
//特别感谢 @pbrother 添加此问题并创建所有测试用例。 
// Related Topics 动态规划


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class CombinationSum4 {
    //    List<List<Integer>> res = new ArrayList<>();
    int sum = 0;

    /**
     * 方法一： 回溯法 超出时间限制
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        dfs(new ArrayList<Integer>(), target, nums);
        return sum;
    }

    private void dfs(List<Integer> track, int target, int[] nums) {
        if (target == 0) {
            sum++;
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= target) {
                track.add(nums[i]);
                dfs(track, target - nums[i], nums);
                track.remove(track.size() - 1);
            }
        }
    }


    /**
     * 方法二： 动态规划法
     * 状态转移方程：dp[i]= dp[i - nums[0]] + dp[i - nums[1]] + dp[i - nums[2]] + ... （当 [] 里面的数 >= 0）
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum41(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        CombinationSum4 combinationSum4 = new CombinationSum4();
        System.out.println(combinationSum4.combinationSum41(new int[]{1, 2, 3}, 4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
