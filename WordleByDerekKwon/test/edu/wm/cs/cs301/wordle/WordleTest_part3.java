package edu.wm.cs.cs301.wordle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.wm.cs.cs301.wordle.model.WordleModel;
import edu.wm.cs.cs301.wordle.view.WordleFrame;

class WordleTest_part3 {

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
	/**Changes to be made in model package:
	 * WordleModel constructor to be given parameters to determine column count and maximum rows
	 * Statistics constructor to be given int parameter to use different files depending on difficulty (1 for easy, 2 for medium, 3 for hard)
	**/
	//Test code in the model package to ensure the area of the grid and statistics object is correct for each difficulty
	void testDifficultyEasy() {
		//Check if difficulty option works correctly
		//Check if each difficulty has the correct size grid
		//Expected area = 20, expected difficulty = 1, expected value = 21
		int easyArea = 20;
		WordleModel testModelEasy = new WordleModel(4,5);
		int diffExpected = 1;
		int diffActual = testModelEasy.getStatistics().getDiff();
		int actualArea = testModelEasy.getColumnCount() * testModelEasy.getMaximumRows();
		int expected = easyArea + diffExpected;
		int actual = actualArea + diffActual;
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(actual, expected);
	}
	
	void testDifficultyMedium() {
		//Check if difficulty option works correctly
		//Check if each difficulty has the correct size grid
		//Expected area = 30, expected difficulty = 2, expected value = 32
		int medArea = 30;
		WordleModel testModelMed = new WordleModel(5,6);
		int diffExpected = 2;
		int diffActual = testModelMed.getStatistics().getDiff();
		int actualArea = testModelMed.getColumnCount() * testModelMed.getMaximumRows();
		int expected = medArea + diffExpected;
		int actual = actualArea + diffActual;
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(actual, expected);
	}
	
	void testDifficultyHard() {
		//Check if difficulty option works correctly
		//Check if each difficulty has the correct size grid
		//Expected area = 42, expected difficulty = 3, expected value = 45
		int medArea = 42;
		WordleModel testModelHard = new WordleModel(6,7);
		int diffExpected = 3;
		int diffActual = testModelHard.getStatistics().getDiff();
		int actualArea = testModelHard.getColumnCount() * testModelHard.getMaximumRows();
		int expected = medArea + diffExpected;
		int actual = actualArea + diffActual;
		assertEquals(actual, expected);
	}

}
