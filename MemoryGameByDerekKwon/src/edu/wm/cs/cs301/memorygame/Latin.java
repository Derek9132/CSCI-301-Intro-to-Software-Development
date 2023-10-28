package edu.wm.cs.cs301.memorygame;

public class Latin implements Alphabet {

	@Override
	public char[] toCharArray() {
		char[] latin_arr = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','@','&'};
		return latin_arr;
	}

}
