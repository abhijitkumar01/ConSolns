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

import java.util.ArrayList;

/**
 * Compute all possible anagrams/permutations of given String (sequence of characters)
 * @author abhijitkumar2003[at]gmail[dot]com
 *
 */
public class Permutations {
    private boolean[] used;
    private StringBuilder out = new StringBuilder();
    private String in;
    
    /**
     * Calculate all permutations of given String.
     * @param str
     * @return List of permutations of str
     */
    public ArrayList<String> getPermutations(final String str) {
    	in = str;
        used = new boolean[ in.length() ];
        ArrayList<String> permutations = new ArrayList<String>();
        permute(permutations);
        return permutations;
    }
    
    /**
     * Calculate all permutations. Logic is:
     * (Fix 1 character at a time)
     * Fix first element and shuffle remaining characters
     * Fix second element and shuffle remaining characters
     * (.. and so on)
     * (Fix 2 characters at a time)
     * Fix first 2 characters and shuffle others
     * Fix next 2 characters and shuffle others
     * (.. and so on)
     * (Fix 3 characters and so on, shuffle others)
     * @param permuted
     */
	private void permute(ArrayList<String> permuted) {
		if (out.length() == in.length()) {
			permuted.add(out.toString());
			return;
		}
		for (int i = 0; i < in.length(); ++i) {
			if (used[i])
				continue; // skip fixed character
			out.append(in.charAt(i));
			used[i] = true; // fix seen character
			permute(permuted);
			used[i] = false;// make this available for shuffle
			out.setLength(out.length() - 1);
		}
	}
}