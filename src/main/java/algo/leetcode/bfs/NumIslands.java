package algo.leetcode.bfs;//给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设
//网格的四个边均被水包围。 
//
// 示例 1: 
//
// 输入:
//11110
//11010
//11000
//00000
//
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//11000
//11000
//00100
//00011
//
//输出: 3
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class NumIslands {
    /**
     * bfs  找到第一个岛屿放入堆栈，持续广度优先搜索，并标注，直到堆栈为空，计数为1； 再继续找第二个岛屿
     *              执行耗时:6 ms,击败了24.62% 的Java用户
     * 				内存消耗:42.4 MB,击败了5.27% 的Java用户
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        LinkedList<int[]> stack = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    stack.push(new int[]{i, j});
                    ans++;
                }
                while (!stack.isEmpty()) {
                    int[] pop = stack.pollFirst();
                    grid[pop[0]][pop[1]] = '2';
                    for (int k = 0; k < 4; k++) {
                        int newI = pop[0] + dir[k][0];
                        int newJ = pop[1] + dir[k][1];
                        if (newI >= 0 && newJ >= 0 && newI < m && newJ < n && grid[newI][newJ] == '1') {
                            stack.push(new int[]{newI, newJ});
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * dfs   用递归代替栈（java栈代替本地栈）
     *              执行耗时:3 ms,击败了53.00% 的Java用户
     * 				内存消耗:42.7 MB,击败了5.27% 的Java用户
     *
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int islands = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    islands++;
                }
            }
        }
        return islands;
    }

    private void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int k = 0; k < 4; k++) {
            int newI = i + dir[k][0];
            int newJ = j + dir[k][1];
            if (newI >= 0 && newJ >= 0 && newI < grid.length && newJ < grid[0].length && grid[newI][newJ] == '1') {
                dfs(grid, newI, newJ);
            }
        }
    }


    /**
     * 并查集
     * @param grid
     * @return
     */
    public int numIslands3(char[][] grid) {
        if(grid.length == 0) return 0;
        int x = grid.length;
        int y = grid[0].length;

        int[] nums = new int[x * y];
        Arrays.fill(nums, -1);

        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                if(grid[i][j] == '1') {
                    grid[i][j] = '0';

                    //判断下侧是否有陆地
                    if(i < (x - 1) && grid[i + 1][j] == '1') {
                        union(nums, i * y + j, (i + 1) * y + j);
                    }

                    //判断右侧是否有陆地
                    if(j < (y - 1) && grid[i][j + 1] == '1') {
                        union(nums, i * y + j, i * y + j + 1);
                    }
                } else {
                    nums[i * y + j] = -2;
                }
            }
        }

        int count = 0;
        for(int num : nums) {
            if(num == -1) count++;
        }

        return count;
    }

    public int find(int[] parents, int i) {
        if(parents[i] == -1) {
            return i;
        }

        return find(parents, parents[i]);
    }

    public void union(int[] parents, int x, int y) {
        int xset = find(parents, x);
        int yset = find(parents, y);
        if(xset != yset) {
            parents[xset] = yset;
        }
    }


    public static void main(String[] args) {
        System.out.println(new NumIslands().numIslands3(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}}));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
