package edu.wm.cs.cs301.guimemorygame.model;

public class Hiragana implements Alphabet {

	@Override
	public char[] toCharArray() {
		char[] hiragana_arr = {'あ','い','う','え','お','か','き','く','け','こ','さ','し','す','せ','そ','た','ち','つ','て','と','ま','み','む','め','も','な','の','に'};
		return hiragana_arr;
	}

}
