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
        // dp[i][j]就表示第i天对应状态的累计最大收益。 j标识第i天是否持有股票
        int[][] dp = new int[len][2];
        for (int i = 0; i < len; i++) {
            if (i==0){
                dp[0][0] = 0;
                dp[0][1] = -prices[0];
                continue;
            }
            // 第i天不持有股票时最大收益=max(第i-1天不持有的最大收益， 第i-1天持有的最大收益，第i天卖出, 收益增加prices[i])
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            // 第i天持有股票最大收益= 之前就持有（dp[i-1][1]） 或者 第i天买入（第i-2天卖出的利润减去今天买入的价格）
            dp[i][1] = Math.max(dp[i-1][1], i>=2 ? dp[i-2][0]-prices[i] : dp[i-1][0]-prices[i]);
        }
        return dp[len-1][0];
    }
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len<2) {
            return 0;
        }

        int dp[][] = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i = 1; i<len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], i > 1 ? dp[i-2][0] - prices[i] : dp[i-1][0] - prices[i]);
        }
        return dp[len-1][0];
    }

    public int maxProfit1(int[] prices) {
        if(prices.length == 0) return 0;
        // 由于可以无限次交易，所以只定义两个维度，第一个维度是天数
        // 第二个维度表示是否持有股票，0表示不持有（包括本来就不持有和处在冷冻期），1表示持有，2表示进入冷冻期
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        for(int i = 1; i < prices.length; i++)
        {
            // 第i天不持有股票的情况有两种
            // a.第i - 1天也不持有股票
            // b.第i - 1天进入冷冻期（i - 2天卖出）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            // 第i天持有股票有两种情况
            // a.第i - 1天也持有股票
            // b.第i - 1天不持有股票，在第i天买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 第i天进入冷冻期只有一种情况，第i - 1天持有股票且卖出（今天卖出）
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        // 最后最大利润为最后一天，不持有股票或者进入冷冻期的情况 （今天卖出）
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][2]);
    }

    public static void main(String[] args) {
//        System.out.println(new MatProfit_with_cool().maxProfit(new int[]{1,2,3,0,2}));
        System.out.println(new MatProfit_with_cool().maxProfit(new int[]{2,1,4}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
