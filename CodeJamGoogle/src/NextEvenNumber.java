import java.util.ArrayList;
import java.util.Collections;

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
 * 
 * Given a number N, find the smallest even number E such that E > N and digits
 * in N and E are same. Print NONE otherwise.
 * 
 * Sample:
 * 
 * Input
 * N = 34722641
 * 
 * Output
 * E = 34724126
 * 
 * Input
 * N = 8234961
 * 
 * Output
 * E = 8236194 (instead of 8236149)
 * 
 * @author abhijitkumar2003[at]gmail[dot]com
 * 
 */
public class NextEvenNumber {
	public void nextSmallestEvenNumber(final int number) {
		boolean found = false;
		int nextEven = 0;
		String in = String.valueOf(number); // String representation of number
		Permutations permute = new Permutations();
		ArrayList<String> anagrams = new ArrayList<String>();
		anagrams = permute.getPermutations(in); // all anagrams of number
		Collections.sort(anagrams); // all anagrams of number sorted
		for (String str : anagrams) {
			int num = Integer.valueOf(str);
			if (num < number) // discard smaller anagrams
				continue;
			// first smallest even number > input number having same digits as
			// input number
			if (num % 2 == 0) {
				found = true;
				nextEven = num;
				break;
			}
		}
		if (found)
			System.out.println(nextEven);
		else
			System.out.println("NONE");
	}

}
