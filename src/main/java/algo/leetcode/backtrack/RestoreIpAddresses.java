package algo.leetcode.backtrack;//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
//
// 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。 
//
// 
//
// 示例: 
//
// 输入: "25525511135"
//输出: ["255.255.11.135", "255.255.111.35"] 
// Related Topics 字符串 回溯算法 
// 👍 366 👎 0

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class RestoreIpAddresses {
    List<List<String>> ans = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if (s == null)
            return convert(ans);
        backtrack(new ArrayList<>(), s);
        return convert(ans);
    }

    private void backtrack(List<String> list, String left) {
        if (list.size() == 4) {
            if (left == null || left.length() == 0) {
                ans.add(list);
            }
            return;
        }
        if (left.length() == 0) return;
        for (int i=1; i<=left.length(); i++) {
            String select = left.substring(0, i);
            if (isIp(select)) {
                ArrayList<String> newList = new ArrayList<>(list);
                newList.add(select);
                String leftStr = left.substring(i, left.length());
                if (newList.size() == 3 && leftStr.length() > 3) continue;
                backtrack(newList, leftStr);
            } else {
                return;
            }
        }

    }

    private boolean isIp(String str) {
        if (str == null || str.length() == 0) return false;
        if (str.charAt(0) == '0' && str.length() > 1) return false;
        int num = 0;
        for (int i=0; i<str.length(); i++) {
            num = str.charAt(i)-'0'+num*10;
        }
        if (num>=0 && num<=255) return true;
        return false;
    }



    private List<String> convert(List<List<String>> origin) {
        List<String> ans = new ArrayList<>();
        origin.forEach(list -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i < list.size() - 1)
                   sb.append(".");
            }
            ans.add(sb.toString());
        });
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new RestoreIpAddresses().restoreIpAddresses("25525511135"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
