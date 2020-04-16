package algo.leetcode.bfs;

//给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
//
// 两个相邻元素间的距离为 1 。 
//
// 示例 1: 
//输入: 
//
// 
//0 0 0
//0 1 0
//0 0 0
// 
//
// 输出: 
//
// 
//0 0 0
//0 1 0
//0 0 0
// 
//
// 示例 2: 
//输入: 
//
// 
//0 0 0
//0 1 0
//1 1 1
// 
//
// 输出: 
//
// 
//0 0 0
//0 1 0
//1 2 1
// 
//
// 注意: 
//
// 
// 给定矩阵的元素个数不超过 10000。 
// 给定矩阵中至少有一个元素是 0。 
// 矩阵中的元素只在四个方向上相邻: 上、下、左、右。 
// 
// Related Topics 深度优先搜索 广度优先搜索


import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class UpdateMatrix {
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1},{0,1}};
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = -1;
                } else {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] peek = queue.poll();
            for (int i=0; i<4;  i++) {
                int x1 = dir[i][0] + peek[1];
                int y1 = dir[i][1] + peek[0];
                if (inMatrix(x1, y1, matrix) && matrix[y1][x1] == -1) {
                    matrix[y1][x1] = matrix[peek[0]][peek[1]] + 1;
                    queue.offer(new int[]{y1,x1});
                }
            }
        }
        return matrix;

    }

    private boolean inMatrix(int x, int y, int[][] matrix) {
        return x>=0 && x <matrix[0].length && y>=0 && y<matrix.length;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,0,0}, {0,1,0}, {1,1,1}};
        int[][] ints = new UpdateMatrix().updateMatrix(matrix);
        System.out.println(JSON.toJSON(ints));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
