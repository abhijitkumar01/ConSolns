import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class TicTacToeTomek {
	// Create a list of TicTacToe boards
	// size of board = number of Test cases
	private LinkedList<TicTacToeBoard> boards = new LinkedList<TicTacToeBoard>();
	// set winner here (X/O), default to #
	private char winner = '#';

	/**
	 * Get input from STDIN Create TicTacToe boards Populate each TicTacToe
	 * board
	 */
	public void readInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int nbrOfInp = Integer.valueOf(br.readLine());
			for (int i = 0; i < nbrOfInp; i++) {
				TicTacToeBoard board = new TicTacToeBoard();
				board.fillBoard(br);
				boards.addLast(board);
				br.readLine();
			}
			solveTicTacToeBoards();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * helper method
	 * Solve TicTacToe board one by one and print O/P for each board
	 */
	protected void solveTicTacToeBoards() {
		for (int i = 0; i < boards.size(); i++) {
			TicTacToeBoard baord = boards.get(i);
			System.out.println("Case #" + (i + 1) + ": " + solveBoard(baord));
		}
	}

	/**
	 * helper method Solve one TicTacToe board
	 * 
	 * @param board
	 * @return 
	 *  "X won" (the game is over, and X won)
	 *	"O won" (the game is over, and O won)
	 *	"Draw" (the game is over, and it ended in a draw)
	 *	"Game has not completed" (the game is not over yet)
	 */
	private String solveBoard(TicTacToeBoard board) {
		String ret = "";
		winner = '#';
		if (isSolvedDiagonalForward(board)) {
			ret = String.valueOf(winner) + " won";
		}
		if (isSolvedDiagonalBackward(board)) {
			ret = String.valueOf(winner) + " won";
		}
		for (int i = 0; i < board.boardSize; i++) {
			if (isSolvedForward(board, i)) {
				ret = String.valueOf(winner) + " won";
				break;
			}
			if (isSolvedDownward(board, i)) {
				ret = String.valueOf(winner) + " won";
				break;
			}
		}
		if (winner == '#') {
			if (isBoardFull(board)) {
				ret = "Draw";
			} else {
				ret = "Game has not completed";
			}
		}
		return ret;
	}

	/**
	 * helper method Check if this row is solved.
	 * 
	 * @param board
	 * @param startPos
	 * @return
	 */
	private boolean isSolvedForward(TicTacToeBoard board, int startPos) {
		int j = 0;
		char toMatch = board.board[startPos][j];
		boolean matched = true;
		if (toMatch == 'X' || toMatch == 'O') {
			for (; ++j < board.boardSize;)
				matched = matched
						&& ((board.board[startPos][j] == toMatch) || (board.board[startPos][j] == 'T'));
		} else if (toMatch == 'T') {
			toMatch = board.board[startPos][++j];
			if (toMatch != '.')
				for (; ++j < board.boardSize;)
					matched = matched && (board.board[startPos][j] == toMatch);
		}
		if (toMatch == '.')
			matched = !matched;
		if (matched)
			winner = toMatch;
		return matched;
	}

	/**
	 * helper method Check if this column is solved
	 * 
	 * @param board
	 * @param startPos
	 * @return
	 */
	private boolean isSolvedDownward(TicTacToeBoard board, int startPos) {
		int j = 0;
		char toMatch = board.board[j][startPos];
		boolean matched = true;
		if (toMatch == 'X' || toMatch == 'O') {
			for (; ++j < board.boardSize;)
				matched = matched
						&& ((board.board[j][startPos] == toMatch) || (board.board[j][startPos] == 'T'));
		} else if (toMatch == 'T') {
			toMatch = board.board[++j][startPos];
			if (toMatch != '.')
				for (; ++j < board.boardSize;)
					matched = matched && (board.board[j][startPos] == toMatch);
		}
		if (toMatch == '.')
			matched = !matched;
		if (matched)
			winner = toMatch;
		return matched;
	}

	/**
	 * helper method Check if board is solved diagonally starting from 0,0
	 * 
	 * @param board
	 * @return
	 */
	private boolean isSolvedDiagonalForward(TicTacToeBoard board) {
		int j = 0;
		char toMatch = board.board[j][j];
		boolean matched = true;
		if (toMatch == 'X' || toMatch == 'O') {
			for (; ++j < board.boardSize;) {
				matched = matched
						&& ((board.board[j][j] == toMatch) || (board.board[j][j] == 'T'));
			}
		} else if (toMatch == 'T') {
			toMatch = board.board[++j][j];
			if (toMatch != '.')
				for (; ++j < board.boardSize;) {
					matched = matched && (board.board[j][j] == toMatch);
				}
		}
		if (toMatch == '.')
			matched = !matched;
		if (matched)
			winner = toMatch;
		return matched;
	}

	/**
	 * helper method Check if board is solved diagonally starting from 0,4
	 * 
	 * @param board
	 * @return
	 */
	private boolean isSolvedDiagonalBackward(TicTacToeBoard board) {
		int j = 0;
		char toMatch = board.board[j][board.boardSize - 1 - j];
		boolean matched = true;
		if (toMatch == 'X' || toMatch == 'O') {
			for (; ++j < board.boardSize;) {
				matched = matched
						&& ((board.board[j][board.boardSize - 1 - j] == toMatch) || 
								(board.board[j][board.boardSize - 1 - j] == 'T'));
			}
		} else if (toMatch == 'T') {
			toMatch = board.board[++j][board.boardSize - 1 - j];
			if (toMatch != '.')
				for (; ++j < board.boardSize;) {
					matched = matched
							&& (board.board[j][board.boardSize - 1 - j] == toMatch);
				}
		}
		if (toMatch == '.')
			matched = !matched;
		if (matched)
			winner = toMatch;
		return matched;
	}

	/**
	 * helper method Check if there is any empty space in TicTacToe board, empty
	 * space is represented by '.'
	 * 
	 * @param board
	 * @return
	 */
	private boolean isBoardFull(TicTacToeBoard board) {
		boolean emptySpace = false;
		outerloop: for (int i = 0; i < board.boardSize; i++) {
			for (int j = 0; j < board.boardSize; j++) {
				if (board.board[i][j] == '.') {
					emptySpace = true;
					break outerloop;
				}
			}
		}
		return !emptySpace;
	}

	/**
	 * TicTacToe board, stores all moves completed. A 4 x 4 board description
	 * containing 'X', 'O', 'T' and '.' characters (where '.' represents an
	 * empty square), describing the current state of a gam
	 * 
	 * @author AbhiKumar
	 * 
	 */
	private class TicTacToeBoard {
		private final int boardSize;
		private char[][] board;

		public TicTacToeBoard() {
			boardSize = 4;
			board = new char[boardSize][boardSize];
		}

		protected void fillBoard(BufferedReader br) throws IOException {
			for (int i = 0; i < boardSize; i++) {
				String input = br.readLine();
				board[i] = input.toCharArray();
			}
		}

	}
}
