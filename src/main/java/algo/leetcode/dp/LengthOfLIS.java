package algo.leetcode.dp;//给定一个无序的整数数组，找到其中最长上升子序列的长度。
//
// 示例: 
//
// 输入: [10,9,2,5,3,7,101,18]
//输出: 4 
//解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。 
//
// 说明: 
//
// 
// 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 
// 你算法的时间复杂度应该为 O(n2) 。 
// 
//
// 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗? 
// Related Topics 二分查找 动态规划


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

//leetcode submit region begin(Prohibit modification and deletion)
class LengthOfLIS {
    /**
     * 方法一： 动态规划
     * <p>
     * 将 dp 数组定义为：以 nums[i] 结尾的最长上升子序列的长度
     * 关键字是：以第 i 个数字为结尾，即我们要求 nums[i] 必须被选取。
     * dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            int masDj = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    masDj = Math.max(dp[j], masDj);
                }
            }
            dp[i] = masDj + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 方法二：贪心+二分查找
     * 考虑一个简单的贪心，如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小。
     * 每个元素 tails[k]tails[k] 的值代表 长度为 k+1k+1 的子序列尾部元素的值
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > tails[res]) {
                tails[++res] = nums[i];
            } else {
                int low = 0;
                int high = res;
                int mid;
                while (low <= high) {
                    mid = (high + low) >> 1;
                    if (nums[i] > tails[mid]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                tails[low] = nums[i];
            }
        }
        return res+1;
    }


    public static void main(String[] args) {
        System.out.println(new LengthOfLIS().lengthOfLIS2(new int[]{3,5,6,2,5,4,19,5,6,7,12}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
