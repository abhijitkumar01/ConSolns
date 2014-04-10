
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
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Solution to Google Code Jam 2012 problem.
 * 
 * https://code.google.com/codejam/contest/1460488/dashboard#s=p0&a=0
 * 
 * @author abhijitkumar2003[at]gmail[dot]com
 *
 */
public class SpeakingInTongues {
	private HashMap<Character, Character> transpositionMap = new HashMap<Character, Character>();
	LinkedList<String> inputs = new LinkedList<String>();
	
	public SpeakingInTongues() {
		// Create map, where key is cahracter from Googlerese
		// and value is its mapping in english
		transpositionMap.put('a', 'y');
		transpositionMap.put('b', 'h');
		transpositionMap.put('c', 'e');
		transpositionMap.put('d', 's');
		transpositionMap.put('e', 'o');
		transpositionMap.put('f', 'c');
		transpositionMap.put('g', 'v');
		transpositionMap.put('h', 'x');
		transpositionMap.put('i', 'd');
		transpositionMap.put('j', 'u');
		transpositionMap.put('k', 'i');
		transpositionMap.put('l', 'g');
		transpositionMap.put('m', 'l');
		transpositionMap.put('n', 'b');
		transpositionMap.put('o', 'k');
		transpositionMap.put('p', 'r');
		transpositionMap.put('q', 'z');
		transpositionMap.put('r', 't');
		transpositionMap.put('s', 'n');
		transpositionMap.put('t', 'w');
		transpositionMap.put('u', 'j');
		transpositionMap.put('v', 'p');
		transpositionMap.put('w', 'f');
		transpositionMap.put('x', 'm');
		transpositionMap.put('y', 'a');
		transpositionMap.put('z', 'q');
		transpositionMap.put(' ', ' ');
	}
	
	/**
	 * Read input from STDIN
	 */
	public void readInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int nbrOfCases = Integer.parseInt(br.readLine());
			for(int i = 0; i < nbrOfCases; i++) {
				String input = br.readLine();
				inputs.add(i, input);
			}
			for(int i = 0; i < nbrOfCases; i++) {
				System.out.println("Case #" + (i +1) + ": " + decodeGooglerese(inputs.get(i)));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Decode Googlerese to plain English
	 * @param s (Googlerese string)
	 * @return (string translated in English)
	 */
	private String decodeGooglerese(String s) {
		char[] chars = s.toCharArray();
		StringBuilder sb = new StringBuilder(s.length());
		for(char c: chars) {
			sb.append(transpositionMap.get(c));
		}
		return sb.toString();
	}
	
}
