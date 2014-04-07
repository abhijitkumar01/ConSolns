import java.util.ArrayList;

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
 * Given two words, determine if the first word, or any anagram of it, appears
 * in consecutive characters of the second word. For instance, tea appears as an
 * anagram in the last three letters of slate, but let does not appear as an
 * anagram in actor even though all the letters of let appear in slate. 
 * Return the anagram of the first word that has appeared in the second word.
 * 
 * @author abhijitkumar2003[at]gmail[dot]com
 * 
 */
public class AnagaramsSearcher {

	public void containsAnagramsOf(final String pattern, final String match) {
		Permutations permute = new Permutations();
		ArrayList<String> anagrams = new ArrayList<String>();
		anagrams = permute.getPermutations(pattern);

		boolean found = false;
		for (String str : anagrams) {
			if (match.contains(str)) {
				found = true;
				System.out.println(str);
				break;
			}
		}
		if (!found) {
			System.out.println("NONE");
		}
	}
}
