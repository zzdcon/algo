package algo.leetcode.slidingwindow;//给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 输入：
//  s = "barfoothefoobarman",
//  words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 输入：
//  s = "wordgoodgoodgoodbestword",
//  words = ["word","good","best","word"]
//输出：[]
// 
// Related Topics 哈希表 双指针 字符串


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class FindSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return ans;
        }
        int left = 0, right=0;

        Map<String, Integer> needs = new HashMap<>();
        Map<String, Integer> window;

        for (String word : words) {
            Integer cnt = needs.get(word);
            needs.put(word, cnt == null ? 1 : cnt + 1);
        }
        int wl = words[0].length();
        int match;
        while (left+wl <= s.length()) {
            window = new HashMap<>();
            match = 0;
            String sub = s.substring(left, left+wl);
            if (!needs.containsKey(sub)) {
                left++;
                continue;
            }
            right=left;
            while (right+wl<=s.length()) {
                String sub1 = s.substring(right, right+wl);
                if (!needs.containsKey(sub1)) {
                    break;
                } else {
                    Integer wc = window.get(sub1);
                    window.put(sub1, wc==null? 1 : wc+1);
                    if(window.get(sub1) == needs.get(sub1)) {
                        match++;
                    } else if (window.get(sub1) > needs.get(sub1)) {
                        break;
                    }
                }
                if (match == needs.size()) {
                    ans.add(left);
                    break;
                }
                right += wl;
            }
            left++;
        }
        return ans;
    }

    public static void main(String[] args) {
        //  s = "barfoothefoobarman",
        //  words = ["foo","bar"]
//        System.out.println(new FindSubstring().findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));

        //  s = "wordgoodgoodgoodbestword",
        //  words = ["word","good","best","word"]
//        System.out.println(new FindSubstring().findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
//        System.out.println(new FindSubstring().findSubstring("aaa", new String[]{"a","a"}));
        System.out.println(new FindSubstring().findSubstring("abaababbaba", new String[]{"ab","ba","ab","ba"}));

    }
}
//leetcode submit region end(Prohibit modification and deletion)
