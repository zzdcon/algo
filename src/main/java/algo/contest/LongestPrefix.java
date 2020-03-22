package algo.contest;

//「快乐前缀」是在原字符串中既是 非空 前缀也是后缀（不包括原字符串自身）的字符串。
//
//        给你一个字符串 s，请你返回它的 最长快乐前缀。
//
//        如果不存在满足题意的前缀，则返回一个空字符串。
//
//
//
//        示例 1：
//
//        输入：s = "level"
//        输出："l"
//        解释：不包括 s 自己，一共有 4 个前缀（"l", "le", "lev", "leve"）和 4 个后缀（"l", "el", "vel", "evel"）。最长的既是前缀也是后缀的字符串是 "l" 。
//        示例 2：
//
//        输入：s = "ababab"
//        输出："abab"
//        解释："abab" 是最长的既是前缀也是后缀的字符串。题目允许前后缀在原字符串中重叠。
//        示例 3：
//
//        输入：s = "leetcodeleet"
//        输出："leet"
//        示例 4：
//
//        输入：s = "a"
//        输出：""
//
//
//        提示：
//
//        1 <= s.length <= 10^5
//        s 只含有小写英文字母
class LongestPrefix {
    public String longestPrefix(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String s1 = s.replaceAll(s.substring(0, 1), "");
        if (s1.length() == 0) {
            return s.substring(0, s.length() - 1);
        }
        int end = 1;
        int right = s.lastIndexOf(s.substring(0, end));
        if (right == -1) {
            return "";
        }
        int left = s.length() - right;
        int ans = 0;
        while (left <= s.length() && right > 0) {
            if (s.substring(0, left).equals(s.substring(right, s.length()))) {
                ans = left;
                right = s.substring(0, right).lastIndexOf(s.substring(0, end));
            } else {
                end++;
                right = s.lastIndexOf(s.substring(0, end));

            }
            if (right == -1) {
                break;
            } else {
                left = s.length() - right;
            }
        }
        return s.substring(0, ans);
    }

    public String longestPrefix2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int end = 1;
        int realEnd = 0;
        while (end < s.length()){
            int i1 = s.lastIndexOf(s.substring(0, end));
            if (i1 > 1 && end == s.length()-i1) {
                realEnd = end;
            }
            end++;
        }
        return s.substring(0, realEnd);

    }

    public static void main(String[] args) {
        System.out.println(new LongestPrefix().longestPrefix2("cbcbcba"));
        System.out.println(new LongestPrefix().longestPrefix2("bba"));
        System.out.println(new LongestPrefix().longestPrefix2("ababab"));
        System.out.println(new LongestPrefix().longestPrefix2("leetcodeleet"));
        System.out.println(new LongestPrefix().longestPrefix2("a"));
    }
}