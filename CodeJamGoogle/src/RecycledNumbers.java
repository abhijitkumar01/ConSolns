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
import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Solution to Google code jam problem Recycled Numbers.
 * https://code.google.com/codejam/contest/1460488/dashboard#s=p2&a=0
 * 
 * @author abhijitkumar2003[at]gmail[dot]com
 * 
 */
public class RecycledNumbers {
	private BigInteger[][] nbrRange;

	/**
	 * Read input from STDIN and solve the problem
	 */
	public void readInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int nbrOfCases = Integer.parseInt(br.readLine());
			nbrRange = new BigInteger[nbrOfCases][2];
			for (int i = 0; i < nbrOfCases; i++) {
				String[] ranges = br.readLine().split("\\s");
				nbrRange[i][0] = new BigInteger(ranges[0]);
				nbrRange[i][1] = new BigInteger(ranges[1]);
			}
			for (int i = 0; i < nbrOfCases; i++) {
				System.out.println("Case #"
						+ (i + 1)
						+ ": "
						+ countRecycledNbrsInRange(nbrRange[i][0],
								nbrRange[i][1]));
//				 System.out.println("Case #"
//				 + (i + 1)
//				 + ": "
//				 + solve(nbrRange[i][0].intValue(),
//				 nbrRange[i][1].intValue()));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calculate nbr of recycled pairs (n, m) in given range (A, B) such that
	 * A <= n < m <= B
	 * @param low
	 * @param high
	 * @return
	 */
	private int countRecycledNbrsInRange(BigInteger low, BigInteger high) {
		// if upper range is 10, then there are no such pairs
		if (high.compareTo(new BigInteger("10")) <= 0)
			return 0;
		// numbers with leading digits are not allowed
		if (low.toString().charAt(0) == '0' && high.toString().charAt(0) == '0')
			return 0;
		// keep track all pairs (n, m) in (A, B)
		Set<RecycledPair> recycledPairs = new LinkedHashSet<RecycledPair>();
		// count logic && generate pairs (n, m)
		while (low.compareTo(high) <= 0) {
			String number = low.toString();
			for (int i = 0; i < number.length() - 1; i++) {
				String copy = number;
				String subStr = copy.substring(0, i + 1);
				copy = copy.substring(i + 1);
				String recycled = copy + subStr; // create recycled number
				// is it valid
				if (recycled.charAt(0) == '0') // leading 0 not allowed
					continue;
				BigInteger recycledNbr = new BigInteger(recycled);
				// found a recycled number between (A, B)
				if (recycledNbr.compareTo(low) > 0
						&& recycledNbr.compareTo(high) <= 0) {
					// add this pair (n, m) to our list
					RecycledPair pair = new RecycledPair(low, recycledNbr);
					if (!recycledPairs.contains(pair.reverse()))
						recycledPairs.add(pair);
				}
			}
			low = low.add(new BigInteger("1"));
		}
		// for debugging purpose
//		for (RecycledPair rec : recycledPairs) {
//			System.out.println(rec.num1 + " ==> " + rec.num2);
//		}
		return recycledPairs.size(); // return count of (n, m)
	}

	/**
	 * Represents a pair (n, m). Where m has been created by rotating some of the digits of n in same sequence
	 * 
	 * @author abhijitkumar2003[at]gmail[dot]com
	 *
	 */
	private class RecycledPair implements Comparable<RecycledPair> {
		private BigInteger num1;
		private BigInteger num2;

		/**
		 * @param num1
		 * @param num2
		 */
		public RecycledPair(BigInteger num1, BigInteger num2) {
			this.num1 = num1;
			this.num2 = num2;
		}

		/**
		 * Reverse the pair (n,m)
		 * @return (m, n)
		 */
		public RecycledPair reverse() {
			if (this == null)
				return null;
			return new RecycledPair(this.num2, this.num1);
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(RecycledPair that) {
			if (that.equals(this))
				return 0;
			if (that.num1.equals(this.num1))
				return that.num2.compareTo(this.num2);
			return that.num1.compareTo(this.num1);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((num1 == null) ? 0 : num1.hashCode());
			result = prime * result + ((num2 == null) ? 0 : num2.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof RecycledPair)) {
				return false;
			}
			RecycledPair other = (RecycledPair) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (num1 == null) {
				if (other.num1 != null) {
					return false;
				}
			} else if (!num1.equals(other.num1)) {
				return false;
			}
			if (num2 == null) {
				if (other.num2 != null) {
					return false;
				}
			} else if (!num2.equals(other.num2)) {
				return false;
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "RecycledPair [num1=" + num1 + ", num2=" + num2 + "]";
		}

		private RecycledNumbers getOuterType() {
			return RecycledNumbers.this;
		}

	}

}
