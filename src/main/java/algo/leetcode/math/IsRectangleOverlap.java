package algo.leetcode.math;//矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
//
// 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。 
//
// 给出两个矩形，判断它们是否重叠并返回结果。 
//
// 示例 1： 
//
// 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
//输出：true
// 
//
// 示例 2： 
//
// 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
//输出：false
// 
//
// 说明： 
//
// 
// 两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。 
// 矩形中的所有坐标都处于 -10^9 和 10^9 之间。 
// 
// Related Topics 数学


//leetcode submit region begin(Prohibit modification and deletion)
class IsRectangleOverlap {

    /**
     * 方法一
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x1 = rec1[0];
        int y1 = rec1[1];
        int x2 = rec1[2];
        int y2 = rec1[3];

        int x11 = rec2[0];
        int y11 = rec2[1];
        int x22 = rec2[2];
        int y22 = rec2[3];


       return  ((x1 > x11 && x1 < x22) || (x2 > x11 & x2 < x22) || (x1<=x11 && x2 >=x22))
                &&
                ((y1 > y11 && y1 < y22) || (y2 > y11 & y2 < y22) || (y1<=y11 && y2 >=y22));
    }


    /**
     * 一个矩阵在另一个矩阵的上、下、左、右就不会重叠
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        int x1 = rec1[0];
        int y1 = rec1[1];
        int x2 = rec1[2];
        int y2 = rec1[3];

        int x11 = rec2[0];
        int y11 = rec2[1];
        int x22 = rec2[2];
        int y22 = rec2[3];
        return !(x1 >= x22 || x2 <= x11 || y1>= y22 || y2<= y11);
    }

    /**
     * 方法三： 判断线段重叠
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap3(int[] rec1, int[] rec2) {
        int x1 = rec1[0];
        int y1 = rec1[1];
        int x2 = rec1[2];
        int y2 = rec1[3];

        int x11 = rec2[0];
        int y11 = rec2[1];
        int x22 = rec2[2];
        int y22 = rec2[3];
        return Math.min(x2, x22) > Math.max(x1, x11) && Math.min(y2, y22) > Math.max(y1, y11);
    }

        public static void main(String[] args) {
        System.out.println(new IsRectangleOverlap().isRectangleOverlap(new int[]{7,8,13,15},
                new int[]{10,8,12,20}));

        System.out.println(new IsRectangleOverlap().isRectangleOverlap(new int[]{0,0,1,1},
                new int[]{1,0,2,1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
