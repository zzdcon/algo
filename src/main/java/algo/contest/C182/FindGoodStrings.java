package algo.contest.C182;


//给你两个长度为 n 的字符串 s1 和 s2 ，以及一个字符串 evil 。请你返回 好字符串 的数目。
//
//        好字符串 的定义为：它的长度为 n ，字典序大于等于 s1 ，字典序小于等于 s2 ，且不包含 evil 为子字符串。
//
//        由于答案可能很大，请你返回答案对 10^9 + 7 取余的结果。
//
//
//
//        示例 1：
//
//        输入：n = 2, s1 = "aa", s2 = "da", evil = "b"
//        输出：51
//        解释：总共有 25 个以 'a' 开头的好字符串："aa"，"ac"，"ad"，...，"az"。还有 25 个以 'c' 开头的好字符串："ca"，"cc"，"cd"，...，"cz"。最后，还有一个以 'd' 开头的好字符串："da"。
//        示例 2：
//
//        输入：n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet"
//        输出：0
//        解释：所有字典序大于等于 s1 且小于等于 s2 的字符串都以 evil 字符串 "leet" 开头。所以没有好字符串。
//        示例 3：
//
//        输入：n = 2, s1 = "gx", s2 = "gz", evil = "x"
//        输出：2
//
//
//        提示：
//
//        s1.length == n
//        s2.length == n
//        1 <= n <= 500
//        1 <= evil.length <= 50
//        所有字符串都只包含小写英文字母。
class FindGoodStrings {
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        if (s1.indexOf(evil) == 0 && s2.indexOf(evil) == 0) {
            return 0;
        }

        int ans = 0;
        int value1 = getNumFroStr(s1);
        int value2 = getNumFroStr(s2);
        int cntEvel = 0;
        int i=0;
        int evilNum = getNumFroStr(evil);
        while (evilNum * Math.pow(25, i) <= value1) {
            cntEvel += getNumFroStr(s1.substring(0, s1.length()-evil.length()-i));
            cntEvel += getNumOfStrLen(i);
            i++;
        }
        ans = value2 - value1 - cntEvel;
        return ans;
    }

    private double getNumOfStrLen(int num) {
        return Math.pow(24, num)-1;
    }

    private int getNumFroStr(String s) {
        int value = 0;
        for (int i = 0; i<s.length(); i++) {
            value += (s.charAt(i) - 'a' + 1) + value * Math.pow(25, i);
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(new FindGoodStrings().findGoodStrings(2, "aa", "da", "b"));
    }
}