package algo.leetcode.dp;

//给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
//
// 示例 1: 
//输入: 
//
// 
//"bbbab"
// 
//
// 输出: 
//
// 
//4
// 
//
// 一个可能的最长回文子序列为 "bbbb"。 
//
// 示例 2: 
//输入: 
//
// 
//"cbbd"
// 
//
// 输出: 
//
// 
//2
// 
//
// 一个可能的最长回文子序列为 "bb"。 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class LongestPalindromeSubseq {
    /**
     * 动态规划
     * if (s[i] == s[j])
     *     // 它俩一定在最长回文子序列中
     *     dp[i][j] = dp[i + 1][j - 1] + 2;
     * else
     *     // s[i+1..j] 和 s[i..j-1] 谁的回文子序列更长？
     *     dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
     * 至此，状态转移方程就写出来了，根据 dp 数组的定义，我们要求的就是 dp[0][n - 1]，也就是整个 s 的最长回文子序列的长度。
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int n=s.length();
        int[][] dp = new int[n][n];
        for (int i=n-1; i>=0; i--) {
            dp[i][i] = 1;
            for (int j=i+1; j<n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][n-1];

    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromeSubseq().longestPalindromeSubseq("bbbab"));
        System.out.println(new LongestPalindromeSubseq().longestPalindromeSubseq("cbbd"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
