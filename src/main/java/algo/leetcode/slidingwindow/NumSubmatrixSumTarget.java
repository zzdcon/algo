package algo.leetcode.slidingwindow;

//给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
//
// 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。 
//
//
// 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵
//也不同。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
//输出：4
//解释：四个只含 0 的 1x1 子矩阵。
// 
//
// 示例 2： 
//
// 输入：matrix = [[1,-1],[-1,1]], target = 0
//输出：5
//解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= matrix.length <= 300 
// 1 <= matrix[0].length <= 300 
// -1000 <= matrix[i] <= 1000 
// -10^8 <= target <= 10^8 
// 
// Related Topics 数组 动态规划 Sliding Window


import com.alibaba.fastjson.JSON;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class NumSubmatrixSumTarget {


    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return 0;
        }
        return 0;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 5,2};
        Arrays.sort(a);
        System.out.println(JSON.toJSON(a));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
