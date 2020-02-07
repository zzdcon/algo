package algo.leetcode.array;//给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。 
//
// 
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 
//
// 示例: 
//
// 输入: [1,8,6,2,5,4,8,3,7]
//输出: 49 
// Related Topics 数组 双指针


//leetcode submit region begin(Prohibit modification and deletion)
class MaxArea {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = height[left] < height[right] ? (right - left) * height[left++]
                    : (right - left) * height[right --];
        }
        return max;
    }

    public int maxArea1(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            if(height[left] < height[right]) {
                max = Math.max(max, (right - left) * height[left]);
                int lastLeft = height[left];
                left++;
                while (height[left] <= lastLeft && left<right) {
                    left++;
                }
            } else {
                max = Math.max(max, (right - left) * height[right]);
                int lastRight = height[right];
                right--;
                while (height[right] <= lastRight && left<right) {
                    right--;
                }
            }
        }
        return max;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
