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


//leetcode submit region begin(Prohibit modification and deletion)
class LargestRectangleArea {

    /**
     * 是以i 为中心，向左找第一个小于 heights[i] 的位置 left_i；
     * 向右找第一个小于于 heights[i] 的位置 right_i，
     * 即最大面积为 heights[i] * (right_i - left_i -1)
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights.length ==0 ) {
            return 0;
        }

        int maxArea = 0;
        for (int i=0; i<heights.length; i++) {
            int left=i-1;
            int right=i+1;
            while (left>=0 && heights[left] >= heights[i]) {
                left--;
            }
            while (right<heights.length && heights[right]>=heights[i]) {
                right++;
            }
            maxArea = Math.max(maxArea, (right-left-1)*heights[i]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new LargestRectangleArea().largestRectangleArea(new int[]{2,1,5,6,2,3
        }));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
