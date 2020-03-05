package algo.leetcode.slidingwindow;//给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
//
// （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。） 
//
// 返回 A 中好子数组的数目。 
//
// 
//
// 示例 1： 
//
// 输出：A = [1,2,1,2,3], K = 2
//输入：7
//解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
// 
//
// 示例 2： 
//
// 输入：A = [1,2,1,3,4], K = 3
//输出：3
//解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 20000 
// 1 <= A[i] <= A.length 
// 1 <= K <= A.length 
// 
// Related Topics 哈希表 双指针 Sliding Window


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class SubarraysWithKDistinct {

    /**
     * 方法一， 每次都固定窗口大小，超出时间限制(40 / 55 个通过测试用例)
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int left = 0, right = 0;
        int ans = 0;
        int len = A.length;
        for (int k = K; k<=len; k++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            left = 0;
            right = 0;
            while (right<len) {
                map.put(A[right], map.getOrDefault(A[right], 0) + 1);
                if (right - left + 1 == k) {
                    if (map.size() == K){
                        ans++;
                    }
                    map.put(A[left], map.get(A[left])-1);
                    if (map.get(A[left]) == 0) {
                        map.remove(A[left]);
                    }
                    left++;
                }
                right++;
            }
        }
        return ans;
    }

    /**
     * 不固定窗口，固定left, 移动right, 超出时间限制(45 / 55 个通过测试用例)
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct2(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int left = 0, right = 0;
        int ans = 0;
        int len = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        while (right < len && left+K <= len) {
            map.put(A[right], map.getOrDefault(A[right], 0) + 1);
            if (map.size() == K) {
                // 窗口内不同整数的数量等于K，结果加一
                ans++;
            }
            if (map.size()>K || right == len-1) {
                // 窗口内超出不同整数的数量超出数字K 或者 right到达数据尾部，left右移，初始化right为left
                left++;
                right=left;
                map.clear();
                continue;
            }
            right++;
        }
        return ans;
    }

    /**
     * 满足K时， 固定left, 移动right, 再固定right移动left，两者相乘
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct3(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int left = 0, right = 0;
        int ans = 0;
        int len = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        while (right<len) {
            map.put(A[right], map.getOrDefault(A[right], 0) + 1);
            if (map.size() == K) {
                int nr = 1;
                while (right+nr < len && map.get(A[right+nr]) != null) {
                    nr++;
                }
                int nl = 0;
                while (map.getOrDefault(A[left+nl], 0) > 1) {
                    map.put(A[left+nl], map.get(A[left+nl])-1);
                    if (map.get(A[left+nl]) == 0) {
                        map.remove(A[left+nl]);
                    }
                    nl++;
                }
                ans += (nl+1)*nr;
                map.put(A[left+nl], map.get(A[left+nl])-1);
                if (map.get(A[left+nl]) == 0) {
                    map.remove(A[left+nl]);
                }
                left += nl + 1;
            }
            right++;
        }
        return ans;
    }

    public int subarraysWithKDistinct4(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int res = 0;
        int l = 0, r = 0;
        for (; r < A.length; ++r) {
            int rC = map.getOrDefault(A[r], 0);
            count += (rC == 0 ? 1 : 0);
            map.put(A[r], rC + 1);
            if (count == K) {
                int nR = r + 1;
                while (nR < A.length && map.getOrDefault(A[nR], 0) > 0) {
                    ++nR;
                }
                int nL = l;
                while (map.get(A[nL]) > 1) {
                    map.put(A[nL], map.get(A[nL]) - 1);
                    ++nL;
                }
                res += (nL - l + 1) * (nR - r);
                map.put(A[nL], 0);
                l = nL + 1;
                --count;
            }
        }
        return res;
    }

    public static void main(String[] args) {

//        System.out.println(new SubarraysWithKDistinct().subarraysWithKDistinct2(new int[] {1,2,1,2,3}, 2));
//        System.out.println(new SubarraysWithKDistinct().subarraysWithKDistinct2(new int[] {1,2,1,3,4}, 3));
//        System.out.println(new SubarraysWithKDistinct().subarraysWithKDistinct2(new int[] {2, 1,2, 1,2}, 2));


        System.out.println(new SubarraysWithKDistinct().subarraysWithKDistinct3(new int[] {1,2,1,2,3}, 2));
        System.out.println(new SubarraysWithKDistinct().subarraysWithKDistinct3(new int[] {1,2,1,3,4}, 3));
        System.out.println(new SubarraysWithKDistinct().subarraysWithKDistinct3(new int[] {2, 1,2, 1,2}, 2));
    }
}

//leetcode submit region end(Prohibit modification and deletion)
