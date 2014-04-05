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
 * Solution for Fair and Square.
 * Google code jam 2013 Qual Round
 * (https://code.google.com/codejam/contest/2270488/dashboard#s=p2&a=0).
 * 
 * A number is "fair and square" if it is a palindrome as well as its
 * square root is palindrome.
 * 
 * @author abhijitkumar2003[at]gmail[dot]com
 *
 */
public class FairAndSquare {
	private long[][] inpArray;
	
	/**
	 * Check if a number is palindrome or not.
	 * Convert the number to string and then match it with its reverse.
	 * @param possiblePalindrome
	 * @return
	 */
	protected boolean isPalindrome(long possiblePalindrome) {
		String couldBePalindrome = String.valueOf(possiblePalindrome);
		String reversed = (new StringBuilder(couldBePalindrome)).reverse().toString();
		return couldBePalindrome.equals(reversed);
	}
	
	/**
	 * Square the input nbr and if it lies in the range of input case,
	 * check if it is palindrome or not.
	 * @param possibleNbr
	 * @param low
	 * @param high
	 * @return
	 */
	protected boolean isFairAndSquare(long possibleNbr, long low, long high) {
		long sqrd = possibleNbr * possibleNbr;
		if(sqrd >= low && sqrd <= high) {
			return isPalindrome(sqrd);
		}
		return false;
	}
	
	/**
	 * If a number is palindrome and its square root is also palindrome then 
	 * we have found a fair and square. Count such numbers in given range.
	 * @param low
	 * @param high
	 * @return
	 */
	protected String countOfFairAndSquare(long low, long high) {
		int count = 0;
		// Convert given range to its square root. We do this to handle the 
		// situation where we have very large range like 1 <= A <= B <= 1014
		long lowSqrt = (long) Math.sqrt(low);
		long highSqrt = (long) Math.sqrt(high);
		// find palindromes in range of square root.
		NumPalindromeGenerator generator = new NumPalindromeGenerator();
		long palindrome = lowSqrt;
		while(palindrome <= highSqrt) {
			generator.setNum(palindrome);
			generator.generateNextPalindrome();
			palindrome = generator.getPalindrome();
			// check whether the square of this palindrome is also a palindrome
			// if yes, then it is a fair and square number.
			if(isFairAndSquare(palindrome, low, high)) {
				count++;
			}
		}
		return Integer.valueOf(count).toString();
	}
	
	/**
	 * Read input from STDIN
	 * input format is as described at
	 * (https://code.google.com/codejam/contest/2270488/dashboard#s=p2&a=0)
	 * 
	 * 3 #nbrOfInput cases
	 * 1 4 #case 1
	 * 10 120 #case 2
	 * 100 1000 #case 3
	 */
	public void readInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int nbrOfInp = Integer.valueOf(br.readLine());
			inpArray = new long[nbrOfInp][2];
			for(int i = 0; i < nbrOfInp; i++) {
				String input = br.readLine();
				inpArray[i][0] = Long.valueOf(input.split(" ")[0]);
				inpArray[i][1] = Long.valueOf(input.split(" ")[1]);				
			}
			for(int i = 0; i < nbrOfInp; i++) {
				System.out.println("Case #" + (i + 1) + ": " + countOfFairAndSquare(inpArray[i][0], inpArray[i][1]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
