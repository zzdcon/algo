package algo.leetcode.dp;//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 示例: 
//
// 输入: [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class MatProfit_with_cool {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int len = prices.length;
        int[][] dp = new int[len][2];
        for (int i = 0; i < len; i++) {
            if (i==0){
                dp[0][0] = 0;
                dp[0][1] = -prices[0];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            // 第一次买不用考虑冻结期
            dp[i][1] = Math.max(dp[i-1][1], i>=2 ? dp[i-2][0]-prices[i] : dp[i-1][0]-prices[i]);
        }
        return dp[len-1][0];
    }

    public static void main(String[] args) {
//        System.out.println(new MatProfit_with_cool().maxProfit(new int[]{1,2,3,0,2}));
        System.out.println(new MatProfit_with_cool().maxProfit(new int[]{2,1,4}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
