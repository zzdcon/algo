package algo.leetcode.stack;//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
// 
//
// 
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。 
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
//输出: 10 
// Related Topics 栈 数组


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class LargestRectangleArea {

    /**
     * 枚举以每个柱形为高度的最大矩形的面积
     * 向左找第一个小于 heights[i] 的位置 left_i；
     * 向右找第一个小于于 heights[i] 的位置 right_i，
     * 即最大面积为 heights[i] * (right_i - left_i -1)
     * <p>
     * <p>
     * 时间复杂度为 O(n^2)
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && heights[left] >= heights[i]) {
                left--;
            }
            while (right < heights.length && heights[right] >= heights[i]) {
                right++;
            }
            maxArea = Math.max(maxArea, (right - left - 1) * heights[i]);
        }
        return maxArea;
    }


    /**
     * 单调递增栈
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }

        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] heights2 = new int[heights.length + 2];
        for (int i = 0; i < heights.length; i++) {
            heights2[i + 1] = heights[i];
        }

        for (int i = 0; i < heights2.length; i++) {
            while (!stack.isEmpty() && heights2[stack.peek()] > heights2[i]) {
                Integer index = stack.pop();
                maxArea = Math.max(maxArea, heights2[index] * (i - stack.peek()-1));
            }
            stack.push(i);
        }
        return maxArea;
    }

    public int largestRectangleArea3(int[] heights) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] new_heights = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) new_heights[i] = heights[i - 1];
        //System.out.println(Arrays.toString(new_heights));
        for (int i = 0; i < new_heights.length; i++) {
            //System.out.println(stack.toString());
            while (!stack.isEmpty() && new_heights[stack.peek()] > new_heights[i]) {
                int cur = stack.pop();
                res = Math.max(res, (i - stack.peek() - 1) * new_heights[cur]);
            }
            stack.push(i);
        }
        return res;
    }


    public static void main(String[] args) {
//        System.out.println(new LargestRectangleArea().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
//        System.out.println(new LargestRectangleArea().largestRectangleArea2(new int[]{2, 1, 5, 6, 2, 3}));

//        System.out.println(new LargestRectangleArea().largestRectangleArea(new int[]{2, 1, 2}));
        System.out.println(new LargestRectangleArea().largestRectangleArea2(new int[]{2, 1, 2}));
        System.out.println(new LargestRectangleArea().largestRectangleArea3(new int[]{2, 1, 2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
