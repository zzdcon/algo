package algo.leetcode.str;//字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没
//有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。 
//
// 示例1: 
//
// 
// 输入："aabcccccaaa"
// 输出："a2b1c5a3"
// 
//
// 示例2: 
//
// 
// 输入："abbccd"
// 输出："abbccd"
// 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
// 
//
// 提示： 
//
// 
// 字符串长度在[0, 50000]范围内。 
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class CompressString {
    public String compressString(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        String str = "";
        int i=0;
        while (i<S.length()) {
            int j = i;
            int cnt = 0;
            while (j < S.length() && S.charAt(j) == S.charAt(i)) {
                cnt++;
                j++;
            }
            str = str + S.charAt(i) + cnt;
            i=j;
        }
        return str.length() < S.length() ? str :S;
    }

    public static void main(String[] args) {
        System.out.println(new CompressString().compressString("abbccd"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
