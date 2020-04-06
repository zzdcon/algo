package algo.leetcode.dp;

//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2：
//
// 输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)


class Mindistance {


    /** 自底向上dp
     *
     *
     *  dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数所以，

     *  当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]；

     *  当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1

     *  其中，dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。

     *  第一行，是 word1 为空变成 word2 最少步数，就是插入操作

     *  第一列，是 word2 为空，需要的最少步数，就是删除操作
     *  */

    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1+1][n2+1];

        // word2为空时，需要把word1删除，需要删除的次数即0~i的字母个数
        for (int i=0; i<=n1; i++) dp[i][0] = i;
        // word1为空，需要插入操作形成word2, 需要插入的次数即为0~j的字母数
        for (int j=0; j<=n2; j++) dp[0][j] = j;

        for (int i=1; i<=n1; i++) {
            for (int j=1; j<=n2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    // 第i个位置等于第j个位置，所需要的操作等于之前的操作
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    //不相等时，是插入(dp[i][j-1])、删除(dp[i-1][j])、替换(dp[i-1][j-1])的最小值
                    dp[i][j] = Math.min(Math.min(dp[i][j-1],  dp[i-1][j]), dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[n1][n2];
    }

    public static void main(String[] args) {
        System.out.println(new Mindistance().minDistance("horse", "ros"));
        System.out.println(new Mindistance().minDistance("intention", "execution"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
