package algo.leetcode.dfs;


//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
// 如果没有任何一种硬币组合能组成总金额，返回 -1。
//
// 示例 1: 
//
// 输入: coins = [1, 2, 5], amount = 11
//输出: 3 
//解释: 11 = 5 + 5 + 1 
//
// 示例 2: 
//
// 输入: coins = [2], amount = 3
//输出: -1 
//
// 说明: 
//你可以认为每种硬币的数量是无限的。 
// Related Topics 动态规划


import java.util.Arrays;
import java.util.Collections;

//leetcode submit region begin(Prohibit modification and deletion)
class CoinChange {
    /**
     * 方法一： dfs+贪心算法
     * @param coins
     * @param amount
     * @return
     */
    int ans = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        coinChange(coins.length-1, coins, 0, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void coinChange(int index, int[] coins, int count, int needAmount) {
        if (needAmount == 0) {
            ans = Math.min(count, ans);
            return;
        }
        if (index < 0) {
            return;
        }

        int i = needAmount / coins[index];
        for (int k=i; k>=0 && count+k<ans; k--) {
            coinChange(index-1, coins, count+k, needAmount-k*coins[index]);
        }
    }


    /**
     * 方法二：动态规划
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }

        int[] dp = new int[amount+1];
        // 金额为0时， 所需硬币数为0
        dp[0] = 0;
        for (int i = 1; i<= amount; i++) {
            // 计算金额为i时，最少所需硬币数
            int min = Integer.MAX_VALUE;
            for (int j=0; j<coins.length; j++) {
                if (coins[j]<=i && dp[i-coins[j]] < min - 1) {
                    min = dp[i-coins[j]] + 1;
                }
            }
            dp[i] = min;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange2(new int[]{2}, 3));
    }


}
//leetcode submit region end(Prohibit modification and deletion)
