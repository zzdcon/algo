package algo.leetcode.array;//给出一个区间的集合，请合并所有重叠的区间。
//
// 示例 1: 
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
// Related Topics 排序 数组


import com.alibaba.fastjson.JSON;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Merge {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 1) {
            return intervals;
        }

        List<Interval> intervalList = new ArrayList<>();
        for (int[] interval : intervals) {
            Interval var = new Interval();
            var.setStart(interval[0]);
            var.setEnd(interval[interval.length - 1]);
            intervalList.add(var);
        }
        Collections.sort(intervalList, Comparator.comparingInt(Interval::getStart));
        LinkedList<Interval> linkedList = new LinkedList<>();
        intervalList.forEach(interval -> {
            if (linkedList.isEmpty() || interval.getStart()>linkedList.getLast().getEnd()) {
                linkedList.add(interval);
            } else {
                linkedList.getLast().setEnd(Math.max(interval.getEnd(), linkedList.getLast().getEnd()));
            }
        });
        int[][] ans = new int[linkedList.size()][];
        int i=0;
        for (Interval interval : linkedList) {
            ans[i++] = new int[]{interval.getStart(), interval.getEnd()};
        }
        return ans;
    }

    public static class Interval {
        public int start;
        public int end;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

    /**
     * 数组排序
     * @param intervals
     * @return
     */
    public int[][] merge2(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        int idx = -1;
        int[][] res = new int[intervals.length][2];
        for (int i=0; i<intervals.length; i++) {
            if (idx == -1 || intervals[i][0] > res[idx][1]) {
                // 没有相交, 继续
                res[++idx] = intervals[i];
            } else {
                // 有相交
                res[idx][1] = Math.max(intervals[i][1], res[idx][1]);
            }
        }
        return Arrays.copyOf(res, idx+1);
    }

        public static void main(String[] args) {
        int[][] merge = new Merge().merge2(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        System.out.println(JSON.toJSON(merge));
    }


}
//leetcode submit region end(Prohibit modification and deletion)
