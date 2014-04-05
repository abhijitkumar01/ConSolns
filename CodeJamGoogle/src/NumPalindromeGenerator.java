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

/**
 * Generate next smallest palindrome for any given number. This is
 * inspired from and implementation of algo presented at
 * http://www.geeksforgeeks.org/given-a-number
 * -find-next-smallest-palindrome-larger-than-this-number/
 * 
 * @author abhijitkumar2003[at]gmail[dot]com
 * 
 */
public class NumPalindromeGenerator {
	private int[] num;

	/**
	 * @return the palindrome as long
	 */
	public long getPalindrome() {
		long ret = 0;
		for (int i = 0; i < num.length; i++) {
			ret = ret
					+ convertArray2Long(num[num.length - 1 - i],
							i);
		}
		return ret;
	}

	/**
	 * Returns numeric representation of element at a position.
	 * e.g a[] = {5, 6, 7}
	 * 	its numeric representation is 567
	 * 	so, convertArray2Long(7, 0) returns 7
	 * 		convertArray2Long(6, 1) returns 60
	 * 		convertArray2Long(5, 2) returns 500
	 * @param num
	 * @param pos
	 * @return
	 */
	private long convertArray2Long(long num, int pos) {
		if (pos == 0)
			return num;
		long ret = 1;
		for (int i = 0; i < pos; i++) {
			ret = ret * 10;
		}
		return ret * num;
	}

	/**
	 * store given number as an array of int
	 * 
	 * @param input
	 */
	public NumPalindromeGenerator(long input) {
		String temp = String.valueOf(input);
		num = new int[temp.length()];
		for (int i = 0; i < temp.length(); i++) {
			num[i] = temp.charAt(i) - '0';
		}
	}

	/**
	 * Generate next smallest palindrome larger than given number
	 */
	public void generateNextPalindrome() {
		// All the digits are 9, simply o/p 1
		// followed by n-1 0's followed by 1.
		if (areAllNines()) {
			int[] palindrome = new int[num.length + 1];
			palindrome[0] = 1;
			for (int i = 1; i < num.length; i++) {
				palindrome[i] = 0;
			}
			palindrome[num.length] = 1;
			num = new int[palindrome.length];
			num = palindrome;
		} else {
			// index of mid digit
			int mid = num.length / 2;
			// end of left part is always mid -1
			int i = mid - 1;
			// start of right part will be mid or mid +1,
			// depending upon length of number is even or
			// odd
			int j = ((num.length % 2) == 0) ? mid : mid + 1;

			// shall I copy left part to right part
			boolean copyLeft = false;

			// ignore the middle same digits
			while (i >= 0 && j < num.length && num[i] == num[j]) {
				i--;
				j++;
			}

			// have I reached the boundary?
			// is left part digit smaller than right part?
			if (i < 0 || num[i] < num[j]) {
				copyLeft = true;
			}

			// mirror remaining left part to right part
			while (i >= 0) {
				num[j++] = num[i--];
			}

			// if left part digit was smaller than right part digit,
			// then we need to add 1 to middle digit, propagate the
			// carry towards MSB digit of left side. Mirror left part
			// to right part.
			if (copyLeft) {
				int t = mid - 1; // marker to copy left part to right part
				// in case of even number of digits in number, there will be
				// 2 mid elements. I will operate on left mid
				if (num.length % 2 == 0)
					mid--;
				// add 1 to mid element and propagate the carry towards
				// MSB digit of left side
				num[mid] = num[mid] + 1;
				while ((num[mid] / 10) > 0) {
					num[mid] = num[mid] % 10;
					num[mid - 1] = num[mid - 1] + 1;
					mid--;
				}
				// make mirror of left to right
				for (; t >= mid; t--) {
					num[num.length - t - 1] = num[t];
				}
			}
		}
	}

	/**
	 * Check if all digits of given number is 9
	 * @return
	 */
	private boolean areAllNines() {
		for (int i = 0; i < num.length; i++) {
			if (num[i] != 9)
				return false;
		}
		return true;
	}

}
