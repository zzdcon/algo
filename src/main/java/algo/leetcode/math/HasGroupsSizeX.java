package algo.leetcode.math;//给定一副牌，每张牌上都写着一个整数。
//
// 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组： 
//
// 
// 每组都有 X 张牌。 
// 组内所有的牌上都写着相同的整数。 
// 
//
// 仅当你可选的 X >= 2 时返回 true。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3,4,4,3,2,1]
//输出：true
//解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
// 
//
// 示例 2： 
//
// 输入：[1,1,1,2,2,2,3,3]
//输出：false
//解释：没有满足要求的分组。
// 
//
// 示例 3： 
//
// 输入：[1]
//输出：false
//解释：没有满足要求的分组。
// 
//
// 示例 4： 
//
// 输入：[1,1]
//输出：true
//解释：可行的分组是 [1,1]
// 
//
// 示例 5： 
//
// 输入：[1,1,2,2,2,2]
//输出：true
//解释：可行的分组是 [1,1]，[2,2]，[2,2]
// 
//
// 
//提示： 
//
// 
// 1 <= deck.length <= 10000 
// 0 <= deck[i] < 10000 
// 
//
// 
// Related Topics 数组 数学


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//leetcode submit region begin(Prohibit modification and deletion)
class HasGroupsSizeX {

    /**
     * 方法一: 暴力法
     * 			执行耗时:19 ms,击败了16.74% 的Java用户
     * 			内存消耗:42.2 MB,击败了5.64% 的Java用户
     *
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length <= 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        int maxSize = 0;
        for (int num : deck) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            maxSize = Math.max(maxSize, map.get(num));
        }

        for (int i = 2; i <= maxSize; i++) {
            boolean exits = true;
            for (int key : map.keySet()) {
                Integer size = map.get(key);
                if (size < i || size % i != 0) {
                    exits = false;
                    break;
                }
            }
            if (exits) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法二： 最大公约数
     * 				执行耗时:13 ms,击败了46.15% 的Java用户
     * 				内存消耗:42 MB,击败了5.64% 的Java用户
     *
     * @param deck
     */
    public boolean hasGroupsSizeX2(int[] deck) {
        if (deck.length <= 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : deck) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int gcd = 0;
        for (int a : map.keySet()) {
            gcd = gcd(map.get(a), gcd);
        }

        return gcd > 1;

    }

    public int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }

    /**
     * 计数-最大公约数
     *
     * 				执行耗时:6 ms,击败了52.49% 的Java用户
     * 				内存消耗:41.4 MB,击败了5.64% 的Java用户
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX3(int[] deck) {
        if (deck.length <= 1) return false;
        int[] cnt = new int[10000];
        for (int num : deck) {
            cnt[num]++;
        }
        int gcd = 0;
        for (int i = 0; i < 10000; i++) {
            gcd = gcd(cnt[i], gcd);
        }
        return gcd > 1;
    }


    public static void main(String[] args) {
        System.out.println(new HasGroupsSizeX().hasGroupsSizeX(new int[]{1, 1}));
        System.out.println(new HasGroupsSizeX().hasGroupsSizeX(new int[]{0, 0, 0, 0, 0, 1, 1, 2, 3, 4}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
