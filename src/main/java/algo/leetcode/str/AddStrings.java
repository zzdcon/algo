package algo.leetcode.str;//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
//
// 
//
// 提示： 
//
// 
// num1 和num2 的长度都小于 5100 
// num1 和num2 都只包含数字 0-9 
// num1 和num2 都不包含任何前导零 
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式 
// 
// Related Topics 字符串 
// 👍 248 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class AddStrings {
    public String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int carry = 0;
        int sum  = 0;
        String ans = "";
        for(int i=1; i<=Math.max(len1, len2); i++) {
            sum = carry + (len1 < i? 0 : num1.charAt(len1-i) - '0') + (len2 < i? 0 : num2.charAt(len2-i) - '0');
            carry = sum > 10 ? 1 : 0;
            ans = (sum > 10 ? sum - 10 : sum) + ans;
        }
        if (carry == 1) {
            ans = "1"+ans;
        }
        if (ans.length() > 1 && ans.charAt(0) == '0') {
            ans = ans.substring(1,ans.length());
        }
        return ans.length() == 0 ? "0" : ans;
    }

    public static void main(String[] args) {
        System.out.println(new AddStrings().addStrings("0", "0"));
        System.out.println(new AddStrings().addStrings("1", "1"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
