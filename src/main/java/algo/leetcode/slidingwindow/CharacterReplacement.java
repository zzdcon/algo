package algo.leetcode.slidingwindow;

//给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
// 
//
// 注意: 
//字符串长度 和 k 不会超过 104。 
//
// 示例 1: 
//
// 输入:
//s = "ABAB", k = 2
//
//输出:
//4
//
//解释:
//用两个'A'替换为两个'B',反之亦然。
// 
//
// 示例 2: 
//
// 输入:
//s = "AABABBA", k = 1
//
//输出:
//4
//
//解释:
//将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
//子串 "BBBB" 有最长重复字母, 答案为 4。
// 
// Related Topics 双指针 Sliding Window


//leetcode submit region begin(Prohibit modification and deletion)
class CharacterReplacement {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0;
        int[] charMap = new int[128];
        int ans = 0;
        while (right < s.length()) {
            charMap[s.charAt(right)]++;
            ans = Math.max(charMap[s.charAt(right)], ans);
            if (ans + k < right - left + 1) {
                charMap[s.charAt(left)]--;
                left++;
            }
            right++;
        }
        return s.length() - left;
    }

    public static void main(String[] args) {
        System.out.println(new CharacterReplacement().characterReplacement("AABABBA", 1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
