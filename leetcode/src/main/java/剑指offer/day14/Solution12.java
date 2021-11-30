package 剑指offer.day14;

/**
 * <p>
 *  剑指 Offer 12. 矩阵中的路径
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-15 20:43:30
 */
public class Solution12 {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, word, 0, visited)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int si, boolean[][] visited) {
        if (board[i][j] != word.charAt(si)) return false;
        if (si == word.length() - 1) return true;
        visited[i][j] = true;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y]) {
                if (dfs(board, x, y, word, si + 1, visited)) return true;
            }
        }
        visited[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        Solution12 solution12 = new Solution12();
        char[][] board = {{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}};
        String word = "AAB";
        System.out.println(solution12.exist(board, word));
    }

}
