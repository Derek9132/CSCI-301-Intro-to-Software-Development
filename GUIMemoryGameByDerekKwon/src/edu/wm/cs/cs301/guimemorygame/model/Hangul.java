package edu.wm.cs.cs301.guimemorygame.model;

public class Hangul implements Alphabet {

	@Override
	public char[] toCharArray() {
		char[] hangul_arr = {'가','까','아','카','바','자','짜','빠','따','사','다','싸','마','나','라','하','타','차','파','보','뽀','조','도','고','쪼','또','꼬','모'};
		return hangul_arr;
	}

}
