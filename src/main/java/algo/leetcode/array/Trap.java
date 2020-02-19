package algo.leetcode.array;//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针


//leetcode submit region begin(Prohibit modification and deletion)
class Trap {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int area = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if(height[left] >= maxLeft) {
                    maxLeft = height[left];
                }  else {
                    area = area + (maxLeft - height[left]);
                }
                left++;
            } else {
                if(height[right] >= maxRight) {
                    maxRight = height[right];
                }  else {
                    area = area + (maxRight - height[right]);
                }
                right--;
            }

        }
        return area;
    }


    public static void main(String[] args) {
        System.out.println(new Trap().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
