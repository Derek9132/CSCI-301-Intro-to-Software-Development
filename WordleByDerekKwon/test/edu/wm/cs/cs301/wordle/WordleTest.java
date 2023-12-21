package edu.wm.cs.cs301.wordle;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.wm.cs.cs301.wordle.model.WordleModel;

class WordleTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testModel1() {
		WordleModel testModel = new WordleModel(5,6);
		testModel.generateCurrentWord();
		boolean expected = false; //Expected value: Feedback will NOT be given if 
		//String guess1 = "apple";
		String guess2 = "djnwr";
		testModel.setGuess(guess2); //Should fail the test
		//testModel.setGuess(guess1); //Should pass the test
		boolean a = testModel.setCurrentRow();
		boolean test = testModel.getFeedback();
		assertEquals(test, expected);
		//List<String> testList = testModel.getWordList();
		//boolean actual = testList.contains(guess);
		//assertEquals(actual, expected);
	}
	//Needs to verify the game is working 
	//If guess is not a word, do not move onto next row and do not give feedback
	//Should fail before fix, pass after fix

}
