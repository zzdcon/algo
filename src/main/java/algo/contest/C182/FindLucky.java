package algo.contest.C182;

public class FindLucky {
//    在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」。
//
//    给你一个整数数组 arr，请你从中找出并返回一个幸运数。
//
//    如果数组中存在多个幸运数，只需返回 最大 的那个。
//    如果数组中不含幸运数，则返回 -1 。
    public int findLucky(int[] arr) {
        if (arr.length == 0) return -1;
        int[] cnt = new int[501];
        for (int num : arr) {
            cnt[num]++;
        }
        int i=500;
        while (i>0) {
            if (cnt[i] == i) {
                return i;
            }
            i--;
        }
        return -1;
    }
}
