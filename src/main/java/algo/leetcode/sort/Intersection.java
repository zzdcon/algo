package algo.leetcode.sort;//给定两个数组，编写一个函数来计算它们的交集。
//
// 示例 1: 
//
// 输入: nums1 = [1,2,2,1], nums2 = [2,2]
//输出: [2]
// 
//
// 示例 2: 
//
// 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出: [9,4] 
//
// 说明: 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找

import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            if (!set1.contains(i)) {
                set1.add(i);
            }
        }
        for (int j : nums2) {
            if (set1.contains(j) && !set2.contains(j)) {
                set2.add(j);
            }
        }
        int[] result = new int[set2.size()];
        int i=0;
        for (Integer integer : set2) {
            result[i++] = integer;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Intersection().intersection(new int[] {1,2,2,1}, new int[] {2,2}));
    }
}


//leetcode submit region end(Prohibit modification and deletion)
