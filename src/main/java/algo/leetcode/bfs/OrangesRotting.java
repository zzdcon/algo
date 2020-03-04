package algo.leetcode.bfs;//在给定的网格中，每个单元格可以有以下三个值之一：
//
// 
// 值 0 代表空单元格； 
// 值 1 代表新鲜橘子； 
// 值 2 代表腐烂的橘子。 
// 
//
// 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。 
//
// 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[[2,1,1],[1,1,0],[0,1,1]]
//输出：4
// 
//
// 示例 2： 
//
// 输入：[[2,1,1],[0,1,1],[1,0,1]]
//输出：-1
//解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
// 
//
// 示例 3： 
//
// 输入：[[0,2]]
//输出：0
//解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length <= 10 
// 1 <= grid[0].length <= 10 
// grid[i][j] 仅为 0、1 或 2 
// 
// Related Topics 广度优先搜索


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

//leetcode submit region begin(Prohibit modification and deletion)
class OrangesRotting {
    class Orange {
        int x;
        int y;
        int type;

        public Orange(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    private int[][] dict = new int[][]{{0,1}, {0,-1}, {-1,0}, {1, 0}};
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        Queue<Orange> queue = new LinkedList<>();
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Orange(i, j, 2));
                }
            }
        }
        int minutes = 0;
        int depth = queue.size();
        while (!queue.isEmpty()) {
            Orange orange = queue.poll();
            for (int k=0; k<4; k++) {
                int _x = orange.x + dict[k][0];
                int _y = orange.y + dict[k][1];
                if(setRotten(_x, _y, grid)) {
                    queue.add(new Orange(_x, _y, 2));
                }
            }

            depth--;
            if (depth == 0 && !queue.isEmpty()) {
                minutes++;
                depth = queue.size();
            }
        }

        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return minutes;
    }

    private boolean setRotten(int x, int y, int[][] grid) {
        if ( x >= 0 && x<grid.length && y>=0 &&  y< grid[0].length) {
            if (grid[x][y] == 1) {
                grid[x][y] = 2;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new OrangesRotting().orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
    }






    class Pos {
        int x, y, minute;
        public Pos(int _x, int _y, int _minute) {
            x = _x;
            y = _y;
            minute = _minute;
        }
    }
    static int[][] dir = { {-1,0},{1,0},{0,-1},{0,1} };

    public int orangesRotting1(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int minute = 0;
        Queue<Pos> queue = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                if (grid[i][j] == 2)
                    queue.add(new Pos(i, j, minute));
        }
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            minute = pos.minute;
            for (int k = 0; k < 4; k++) {   //一个腐烂，四周受害
                int newX = pos.x + dir[k][0];
                int newY = pos.y + dir[k][1];
                if (newX >= 0 && newX < R && newY >= 0 && newY < C && grid[newX][newY] == 1) {
                    grid[newX][newY] = 2;  //标记腐烂
                    queue.add(new Pos(newX, newY, pos.minute + 1)); //腐烂周期+1
                }
            }
        }
        //check for fresh oranges
        for(int[] row : grid) {
            for (int i : row)
                if (i == 1) return -1;
        }
        return minute;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
