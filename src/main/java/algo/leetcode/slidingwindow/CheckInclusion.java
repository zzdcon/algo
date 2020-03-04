package algo.leetcode.slidingwindow;

// 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
//
// 换句话说，第一个字符串的排列之一是第二个字符串的子串。 
//
// 示例1: 
//
// 
//输入: s1 = "ab" s2 = "eidbaooo"
//输出: True
//解释: s2 包含 s1 的排列之一 ("ba").
// 
//
// 
//
// 示例2: 
//
// 
//输入: s1= "ab" s2 = "eidboaoo"
//输出: False
// 
//
// 
//
// 注意： 
//
// 
// 输入的字符串只包含小写字母 
// 两个字符串的长度都在 [1, 10,000] 之间 
// 
// Related Topics 双指针 Sliding Window


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class CheckInclusion {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            map.put(c, map.getOrDefault(s1.charAt(i), 0) + 1);
        }

        Map<Character, Integer> windows = new HashMap<>();
        int left = 0, right = 0;
        int match = 0;
        // right到S2队尾，窗口移动停止
        while (right < s2.length()) {
            char c = s2.charAt(right);
            Integer cnt = map.get(c);
            //  跳过不匹配的字符
            if (cnt == null) {
                right++;
                left = right;
                // 清空窗口和匹配值计数器
                windows.clear();
                match = 0;
                continue;
            }

            windows.put(c, windows.getOrDefault(c, 0) + 1);
            if (windows.get(c).intValue() == map.get(c).intValue()) {
                // 字符出现次数匹配时，匹配计数器递增
                match++;
            } else if (windows.get(c).intValue() > map.get(c).intValue() || right - left + 1 > s1.length()) {
                // 超出窗口或者单一字符计数超出匹配值，left右移缩小窗口
                char c1 = s2.charAt(left);
                if (windows.get(c1).intValue() == map.get(c1).intValue()) {
                    // left位置字符移除，该位置字符数据量不再匹配时，match减一
                    match--;
                }
                // 移除字符
                windows.put(c1, windows.get(c1) - 1);
                // left 右移
                left++;
            }
            // 字符完全匹配
            if (match == map.size()) {
                return true;
            }
            right++;
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(new CheckInclusion().checkInclusion("ab", "eidbaooo" ));
        System.out.println(new CheckInclusion().checkInclusion("hello", "ooolleoooleh"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
