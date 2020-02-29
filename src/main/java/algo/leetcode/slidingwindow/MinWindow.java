package algo.leetcode.slidingwindow;//给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
//
// 示例： 
//
// 输入: S = "ADOBECODEBANC", T = "ABC"
//输出: "BANC" 
//
// 说明： 
//
// 
// 如果 S 中不存这样的子串，则返回空字符串 ""。 
// 如果 S 中存在这样的子串，我们保证它是唯一的答案。 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class MinWindow {
    public String minWindow(String s, String t) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Integer ni = needs.get(t.charAt(i));
            if (ni == null) {
                needs.put(t.charAt(i), 1);
            } else {
                needs.put(t.charAt(i), ni + 1);
            }
        }

        int left = 0, right = 0;
        int match = 0;
        String ans = "";
        while (right < s.length()) {
            char c = s.charAt(right);
            // 不属于要匹配的字符，跳过
            if (needs.get(c) == null) {
                right++;
                continue;
            }

            // 更新滑动窗口
            Integer wc = windows.get(c);
            windows.put(c, wc == null ? 1 : wc + 1);

            if (windows.get(c).intValue() == needs.get(c).intValue()) {
                match++;
            }

            right++;
            // 移动左边界
            while (match == needs.size()) {
                char c1 = s.charAt(left);
                if (needs.get(c1) == null) {
                    left++;
                    continue;
                }
                if (ans.equals("") || s.substring(left, right).length() < ans.length()) {
                    ans = s.substring(left, right);
                }

                windows.put(c1, windows.get(c1) - 1);
                if (windows.get(c1) < needs.get(c1)) {
                    match--;
                }
                left++;
            }
        }
        return ans;
    }


    /**
     * 作者：wky181
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring/solution/javahua-dong-chuang-kou-dai-ma-you-xiang-xi-zhu-sh/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        //由于字符类型，就考虑所有的ASCII码，因此直接用128个长度的数组代替原来的HashMap,提高性能
        //用来统计t中每个字符出现次数
        int[] needs = new int[128];
        //用来统计滑动窗口中每个字符出现次数
        int[] window = new int[128];
        //统计t中每个字符出现次数
        for (char ch : t.toCharArray()) {
            needs[ch] = needs[ch] + 1;
        }
        int l = 0, r = 0;
        int plength = t.length();
        //候选字符数
        int count = 0;
        int minLength = s.length() + 1;
        String result = "";
        while (r < s.length()) {
            char ch = s.charAt(r);
            //统计滑动窗口中每个字符出现次数
            window[ch] = window[ch] + 1;
            // 当这个字符是在t中，并且t需要的次数大于等于该字符在滑动窗口出现的次数，被确定为候选字符。
            if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                count++;
            }
            //当候选字符数等于t的长度时，说明滑动窗口中的字符串包含T，开始移动l，缩小窗口
            //直到不满足count == plength时，继续扩展窗口
            while (count == plength) {
                ch = s.charAt(l);
                if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                    count--;
                }
                //找出长度最短符合条件的结果 (r-l)+1计算当前窗口大小
                if ((r - l) + 1 < minLength) {
                    minLength = (r - l) + 1;
                    result = s.substring(l, r + 1);
                }
                window[ch] = window[ch] - 1;
                l++;
            }
            r++;
        }
        return result;
    }

    public String minWindow3(String s, String t) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        //由于字符类型，就考虑所有的ASCII码，因此直接用128个长度的数组代替原来的HashMap,提高性能
        //用来统计t中每个字符出现次数
        int[] needs = new int[128];

        //用来统计滑动窗口中每个字符出现次数
        int[] windows = new int[128];
        for (char ch : t.toCharArray()) {
            needs[ch]++;
        }

        int left = 0, right = 0;
        int match = 0;
        String ans = "";
        while (right < s.length()) {
            char c = s.charAt(right);
            // 不属于要匹配的字符，跳过
            if (needs[c] == 0) {
                right++;
                continue;
            }
            // 更新滑动窗口
            windows[c]++;
            if (windows[c] <= needs[c]) {
                match++;
            }
            // 移动左边界
            while (match == t.length()) {
                char c1 = s.charAt(left);
                if (needs[c1] > 0) {
                    if (ans.equals("") || (right-left + 1) < ans.length()) {
                        ans = s.substring(left, right+1);
                    }
                    windows[c1]--;
                    if (windows[c1]<needs[c1]) {
                        match--;
                    }
                }
                left++;
            }
            right++;
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "bdab";
        System.out.println(new MinWindow().minWindow3(s, "ab"));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
