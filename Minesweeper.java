
import java.awt.print.Printable;
import java.util.LinkedList;
import java.util.Queue;

//TC - O(m*n)
//SC - O(m*n)
class Solution {
	public char[][] updateBoard(char[][] board, int[] click) {
		int[][] directions = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };
		int i = click[0];
		int j = click[1];
		if (board[i][j] == 'M') {
			board[i][j] = 'X';
			return board;
		}
		Queue<int[]> q = new LinkedList<>();
		q.add(click);
		board[i][j] = 'B';
		while (!q.isEmpty()) {
			int[] cell = q.poll();

			int count = countMines(board, cell[0], cell[1], directions);
			if (count == 0) { // No mine in neighbours
				for (int[] dir : directions) {
					int nr = cell[0] + dir[0];
					int nc = cell[1] + dir[1];
					if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 'E') {
						q.add(new int[] { nr, nc });
						board[nr][nc] = 'B';
					}
				}
			} else { // mine is present in neighbors
				board[cell[0]][cell[1]] = (char) (count + '0'); // brackets are imp here
			}
		}
		return board;
	}

	public int countMines(char[][] board, int row, int col, int[][] directions) {
		int count = 0;
		for (int[] dir : directions) {
			int nr = row + dir[0];
			int nc = col + dir[1];
			if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 'M') {
				count++;
			}
		}
		return count;
	}
}

public class Minesweeper {

	public static void main(String[] args) {
		Solution s = new Solution();
		char[][] board = { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' },
				{ 'E', 'E', 'E', 'E', 'E' } };
		char[][] mat = s.updateBoard(board, new int[] { 3, 0 });
		print(mat);
		System.out.println();

	}
	public static void print(char[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}

}
