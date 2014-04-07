/**
 *
 *The MIT License (MIT)
 *
 *Copyright (c) 2014 Abhijit Kumar
 *
 *Permission is hereby granted, free of charge, to any person obtaining a copy
 *of this software and associated documentation files (the "Software"), to deal
 *in the Software without restriction, including without limitation the rights
 *to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 *furnished to do so, subject to the following conditions:
 *
 *The above copyright notice and this permission notice shall be included in all
 *copies or substantial portions of the Software.
 *
 *THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *SOFTWARE.
 *
 **/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * In a two-dimensional array, print the indices of the given word. The word can
 * go in any direction, once it is connected (vertical, horizontal, forward and
 * downward only). If the word cannot be found, print 'NONE'.
 * 
 * Input Format:
 *  * First line contains the word that is to be searched. Second line contains X Y
 * denoting X rows of Y columns, Followed by X*Y 2-D array
 * 
 * Note : Treat the array as 1-indexed.
 * 
 * Sample Input 
 * JAVA 
 * 5 5 
 * ESASJ 
 * AJVVA 
 * RAAIA 
 * YVSVA 
 * EARAV
 * 
 * Sample Output 
 * 2 2 
 * 3 2 
 * 4 2 
 * 5 2
 * 
 * @author abhijitkumar2003[at]gmail[dot]com
 * 
 */
public class WordPuzzle {
	private int rows, cols; // nbr of rows and columns
	private char[][] wordArray; // save word puzzle as 2D array

	/**
	 * Read input from STDIN Populate 2D array Search for pattern/word in 2D
	 * array and print the (x,y) co-od of matched letters
	 */
	public void readInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			// read I/P
			String searchString = br.readLine();
			String dmnsn = br.readLine();
			rows = Integer.parseInt(dmnsn.split(" ")[0]);
			cols = Integer.parseInt(dmnsn.split(" ")[1]);
			initializeWordArray();
			fillWordArray(br);
			int[][] matchedPosns = searchInWordArray(searchString);
			// print O/P
			for (int i = 0; i < matchedPosns.length; i++) {
				System.out.print(matchedPosns[i][0] + 1);
				System.out.print(" ");
				System.out.println(matchedPosns[i][1] + 1);
			}
			if (matchedPosns.length == 0) {
				System.out.println("NONE");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void initializeWordArray() {
		wordArray = new char[rows][cols];
	}

	/**
	 * Populate 2D array
	 * 
	 * @param br
	 * @throws IOException
	 */
	private void fillWordArray(BufferedReader br) throws IOException {
		for (int row = 0; row < rows;) {
			String input = br.readLine();
			wordArray[row++] = input.toCharArray();
		}
	}

	/**
	 * Search for pattern/word in 2D array.
	 * 
	 * @param searchWord
	 * @return (x,y) co-od of matched letters
	 */
	protected int[][] searchInWordArray(String searchWord) {
		boolean matched = false;
		int i = 1;
		int[][] matchedPosns = new int[searchWord.length()][2]; // ret value
		int j = 0;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) { // loop over 2D array
				if (wordArray[row][col] == searchWord.charAt(0)) {
					matchedPosns[j][0] = row;
					matchedPosns[j++][1] = col;
					if (col + searchWord.length() - i < cols) {
						int y = col;
						// search forward
						while (i < searchWord.length()
								&& (matched = matchedNextChar(row, ++y,
										searchWord.charAt(i++))) == true) {
							matchedPosns[j][0] = row;
							matchedPosns[j++][1] = y;
						}
						if (!matched) { // not all words found
							j = 1;
							i = 1;
						} else
							return matchedPosns;
					}
					if (row + searchWord.length() - i < rows) {
						// search downward
						int x = row;
						while (i < searchWord.length()
								&& (matched = matchedNextChar(++x, col,
										searchWord.charAt(i++))) == true) {
							matchedPosns[j][0] = x;
							matchedPosns[j++][1] = col;
						}
						if (!matched) { // not all words found
							j = 1;
							i = 1;
						} else
							return matchedPosns;
					}
					j = 0;
				}
			}
		}
		return matchedPosns;
	}

	/**
	 * 2D array[row, col] = letter
	 * 
	 * @param row
	 * @param col
	 * @param word
	 * @return true/false
	 */
	protected boolean matchedNextChar(int row, int col, char word) {
		return (wordArray[row][col] == word);
	}

}
