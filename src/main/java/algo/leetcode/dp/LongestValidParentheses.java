package algo.leetcode.dp;


//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length()];
        int res = 0;
        /**
         * 用 dp[i] 表示以 i 结尾的最长有效括号；
         *
         *     当 s.charAt(i) 为 '('时,不可能组成有效的括号，因此dp[i]= 0；
         *
         *     当 s.charAt(i) 为 '）'时分两种情况
         *
         *              当 s.charAt(i-1) 为 '('，那么 dp[i] = dp[i-2] + 2；
         *
         *               当 s.charAt(i-1) 为 '）'
         *               并且 s.charAt(i-dp[i-1] - 1) 为 '('，那么 dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]；
         *
         */
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (s.charAt(i - 1) == '(') {
                    // '()'
                    dp[i] = i >= 2 ?  dp[i - 2] + 2 : 2;
                } else if (i-dp[i-1]>0 && s.charAt(i-dp[i-1]-1) == '(') {
                    // '))'
                    dp[i] = dp[i-1] + 2 +
                            (i-dp[i-1]-2 >= 0 ?  dp[i-dp[i-1]-2] : 0);
                }

                if (dp[i] > res) {
                    res = dp[i];
                }

            }
        }
        return res;
    }


    /**
     * 方法二： 栈
     *
     * ###分析
     * 这是一个典型的栈的应用类问题
     * 我们用一个栈来存储坐标。为了方便计算，在最开始的时候，我们将栈里面放入一个-1.
     * 当遇到的是'('的时候，我们将其坐标入栈，
     * 当遇到的是'）'的时候，弹出栈顶元素。此时需要分两种情况。
     * 此时如果栈空了，其实相当于前面已经正好匹配了，然后再进来了一个'）',此时无需更新最大值max，
     * 只需将当期坐标入栈。其作用和上面栈初始化的时候放入一个-1相同。
     * 如果此时栈非空，说明又多了一对匹配。需要更新max的值。
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        stack.push(-1);
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i-stack.peek());
                }
            }
        }
        return res;
    }

        public static void main(String[] args) {
        System.out.println(new LongestValidParentheses().longestValidParentheses("()(())"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
