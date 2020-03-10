package algo.leetcode.dp;//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。 
//
// 注意你不能在买入股票前卖出股票。 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
// 
//
// 示例 2: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
// Related Topics 数组 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class MaxProfit_1_trade {
    /**
     * 方法一：动态规划 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }
        return dp[prices.length - 1];
    }

    /**
     * 记录历史最低点，选择当天卖出，与历史的最大利润做比较
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i=1; i<prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i]-minPrice);
            }
        }
        return maxProfit;
    }


    public static void main(String[] args) {
        System.out.println(new MaxProfit_1_trade().maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new MaxProfit_1_trade().maxProfit2(new int[]{7, 6, 4, 3, 1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
