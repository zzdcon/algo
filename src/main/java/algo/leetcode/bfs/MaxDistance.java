package algo.leetcode.bfs;//你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，
//你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。 
//
// 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x
//1| + |y0 - y1| 。 
//
// 如果我们的地图上只有陆地或者海洋，请返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[[1,0,1],[0,0,0],[1,0,1]]
//输出：2
//解释： 
//海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
// 
//
// 示例 2： 
//
// 
//
// 输入：[[1,0,0],[0,0,0],[0,0,0]]
//输出：4
//解释： 
//海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length == grid[0].length <= 100 
// grid[i][j] 不是 0 就是 1 
// 
// Related Topics 广度优先搜索 图


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxDistance {
    /**
     * 方法一：暴力法
     * 记录所有的海洋位置和陆地位置，遍历每一个海洋位置，找到其离陆地最近的距离，从所有距离中找到最大的。
     * 			执行耗时:2709 ms,击败了5.03% 的Java用户
     * 			内存消耗:42 MB,击败了99.00% 的Java用户
     * @param grid
     * @return
     */
    public int maxDistance(int[][] grid) {
        List<Pixel> seas = new ArrayList<>();
        List<Pixel> lands = new ArrayList<>();
        int res = -1;
        for (int i = 0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    seas.add(new Pixel(j,i));
                } else {
                    lands.add(new Pixel(j,i));
                }
            }
        }

        if (seas.size() == 0 || lands.size() == 0) {
            return res;
        }

        for (Pixel sea : seas) {
            res = Math.max(res, nearestDistance(lands, sea));
        }

        return res;
    }

    private int nearestDistance(List<Pixel> lands, Pixel sea) {
        int dist = Integer.MAX_VALUE;
        for (Pixel land : lands) {
            dist = Math.min(Math.abs(land.x-sea.x) + Math.abs(land.y-sea.y), dist);
            //海洋和陆地最多紧挨着，距离不能比1更小了
            if (dist == 1){
                return dist;
            }
        }
        return dist;
    }

    class Pixel {
        int x;
        int y;

        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaxDistance().maxDistance( new  int[][]{{1,0,0},{0,0,0},{0,0,0}}));
        System.out.println(new MaxDistance().maxDistance( new  int[][]{{1,0,1},{0,0,0},{1,0,1}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
