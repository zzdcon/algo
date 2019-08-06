package algo.leetcode.str;//实现 strStr() 函数。
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。 
//
// 示例 1: 
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
// 
//
// 说明: 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。 
//

class StrStr {
    // 方法一： 暴力破解
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        if (haystack == null || haystack.length() == 0) {
            return -1;
        }

        int length = haystack.length();
        for (int i = 0; i<=length - 1; i++) {
            if (length - i < needle.length()) {
                return -1;
            }

            if (haystack.charAt(i) == needle.charAt(0)) {
                boolean isSame = checkMatch(haystack, needle, i);
                if (isSame) {
                    return i;
                }
            }
        }
        return -1;
    }


    private boolean checkMatch(String haystact, String needle, int checkPoint) {
        int i=0;
        while (i<needle.length()) {
            if (haystact.charAt(checkPoint+i) != needle.charAt(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    // 方法2： 
    public int strStr2(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

        public static void main(String[] args) {
        System.out.println(new StrStr().strStr("a", "a"));
    }
}