package algo.leetcode.dp;//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 示例 1: 
//
// 输入: [3,3,5,0,0,3,1,4]
//输出: 6
//解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1] 
//输出: 0 
//解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。 
// Related Topics 数组 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class MaxProfit3 {
    /**
     * 动态规划
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int len = prices.length;
        int[][][]dp = new int[prices.length][3][2];
        for (int i=0; i<len;i++) {
            for (int k=1; k<=2; k++) {
                if (i==0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[0];
                    continue;
                }

                // 注意一点，每次要么在buy时选择交易k发生辩护或者sell时发生变化，下面选择的是buy时发生变化

                // 今天没有持有股票： a. 昨天就没有持有 b. 昨天持有，今天卖了，收益增加
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                // 今天持有股票： a. 昨天就持有股票 b. 昨天没有持有，今天买了，收益减少，交易数增加
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        return dp[len-1][2][0];
    }

    public static void main(String[] args) {
        System.out.println(new MaxProfit3().maxProfit(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(new MaxProfit3().maxProfit(new int[]{1,2,3,4,5}));
        System.out.println(new MaxProfit3().maxProfit(new int[]{7,6,4,3,1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
