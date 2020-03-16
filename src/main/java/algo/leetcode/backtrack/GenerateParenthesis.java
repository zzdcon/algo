package algo.leetcode.backtrack;
//给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
//
// 例如，给出 n = 3，生成结果为： 
//
// [
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
// 
// Related Topics 字符串 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class GenerateParenthesis {
    /**
     * 方法一： 回溯法, 广度优先
     *
     * Sn = "(" + (Sn-1)左  + ")" + (Sn-1)右
     *
     * @param n
     * @return
     */

    Map<Integer, List<String>> ansMap = new HashMap<>();
    public List<String> generateParenthesis(int n) {
        if (ansMap.get(n) != null) {
            return ansMap.get(n);
        }

        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c=0; c<n; c++) {
                for (String left : generateParenthesis(c)) {
                    for (String right : generateParenthesis(n-c-1)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        ansMap.put(n, ans);
        return ans;
    }


    /**
     * 方法二： 回溯法，深度优先搜索
     *    我们可以开始放一个左括号。 如果右括号不超过左括号的数量，我们可以放一个右括号。
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        backtrack("", 0, 0, n, ans);
        return ans;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经用了几个
     * @param right  右括号已经用了几个
     * @param n  要求的括号对数
     * @param ans 当前递归得到的结果
     */
    public void backtrack(String curStr, int left, int right, int n, List<String> ans) {
        if (left == n && right == n) {
            ans.add(curStr);
            return;
        }

        if (left<n) {
            backtrack(curStr+"(", left+1, right, n, ans);
        }

        if (right<left) {
            backtrack(curStr+")", left, right+1, n, ans);
        }
    }

        public static void main(String[] args) {
//        JSONArray objects = JSONArray.parseArray("[\"(((())))\",\"()((()))\",\"((()))()\",\"(()(()))\",\"()()(())\",\"()(())()\",\"((())())\",\"()(())()\",\"(())()()\",\"((()()))\",\"()(()())\",\"(()())()\",\"(()()())\",\"()()()()\"]");
//        JSONArray objects1 = JSONArray.parseArray("[\"(((())))\",\"((()()))\",\"((())())\",\"((()))()\",\"(()(()))\",\"(()()())\",\"(()())()\",\"(())(())\",\"(())()()\",\"()((()))\",\"()(()())\",\"()(())()\",\"()()(())\",\"()()()()\"]");
//        for (Object object : objects) {
//            if (!objects1.contains(object)) {
//                System.out.println("测试不包含" + object);
//            }
//        }
//        System.out.println(JSONArray.toJSONString(objects));
//        System.out.println(JSONArray.toJSONString(objects1));
        System.out.println(new GenerateParenthesis().generateParenthesis(4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
