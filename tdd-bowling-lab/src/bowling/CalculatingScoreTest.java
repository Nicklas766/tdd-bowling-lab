package bowling;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatingScoreTest {

	@Test
	public void testAddFrameCorrectFrame() {
		int throw1 = 5;
		int throw2 = 4;

		int[] frame = CalculatingScore.createFrame(throw1, throw2);

		int[] expectedResult = new int[] {5, 4};

		assertArrayEquals(expectedResult, frame);
	}

	@Test
	public void testAddFrameIncorrectInputs() {
		int throw1 = -1;
		int throw2 = 4;

		Throwable exception = assertThrows(IllegalArgumentException.class, ()->{CalculatingScore.createFrame(throw1, throw2);} );
		assertEquals("A throw has to be between 0 and 10", exception.getMessage());
	}

	@Test
	public void testAddFrameIncorrectInputSum() {
		int throw1 = 5;
		int throw2 = 8;

		Throwable exception = assertThrows(IllegalArgumentException.class, ()->{CalculatingScore.createFrame(throw1, throw2);} );
		assertEquals("The two throws can not be more than 10 combined", exception.getMessage());
	}

	@Test
	// Should return sum of frame, which is 9 in this case
	public void testGetFrameScore() {
		int[] frame    = CalculatingScore.createFrame(5, 4);
		int frameScore = CalculatingScore.getFrameScore(frame);

		assertEquals(frameScore, 9);
	}

	@Test
	public void testCreateGame() {
		int[] frame1 = CalculatingScore.createFrame(1, 5);
		int[] frame2 = CalculatingScore.createFrame(3, 6);
		int[] frame3 = CalculatingScore.createFrame(7, 2);
		int[] frame4 = CalculatingScore.createFrame(3, 6);
		int[] frame5 = CalculatingScore.createFrame(4, 4);
		int[] frame6 = CalculatingScore.createFrame(5, 3);
		int[] frame7 = CalculatingScore.createFrame(3, 3);
		int[] frame8 = CalculatingScore.createFrame(4, 5);
		int[] frame9 = CalculatingScore.createFrame(8, 1);
		int[] frame10 = CalculatingScore.createFrame(2, 6);

		int[] bonusThrows = new int[2];

		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);

		int[][] expectedResult = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows};

		assertArrayEquals(expectedResult, game);
	}

	@Test
	public void testCalculateGame() {
		int[] frame1 = CalculatingScore.createFrame(1, 5);
		int[] frame2 = CalculatingScore.createFrame(3, 6);
		int[] frame3 = CalculatingScore.createFrame(7, 2);
		int[] frame4 = CalculatingScore.createFrame(3, 6);
		int[] frame5 = CalculatingScore.createFrame(4, 4);
		int[] frame6 = CalculatingScore.createFrame(5, 3);
		int[] frame7 = CalculatingScore.createFrame(3, 3);
		int[] frame8 = CalculatingScore.createFrame(4, 5);
		int[] frame9 = CalculatingScore.createFrame(8, 1);
		int[] frame10 = CalculatingScore.createFrame(2, 6);

		int[] bonusThrows = new int[2];

		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);

		int gameScore = CalculatingScore.calculateGameScore(game);

		assertEquals(81, gameScore);
	}

	@Test
	public void testIsStrike() {
		int[] strike = CalculatingScore.createFrame(10, 0);
		int[] notStrike = CalculatingScore.createFrame(0, 0);

		boolean shouldBeStrike = CalculatingScore.isStrike(strike);
		boolean shouldNotBeStrike = CalculatingScore.isStrike(notStrike);

		assertEquals(shouldBeStrike, true);
		assertEquals(shouldNotBeStrike, false);
	}

	@Test
	public void testCalculateStrike() {
		int[] strikeFrame = CalculatingScore.createFrame(10, 0);
		int[] afterStrikeFrame = CalculatingScore.createFrame(4, 3);

		int strikeScore = CalculatingScore.calculateStrike(strikeFrame, afterStrikeFrame);

		assertEquals(strikeScore, 17);
	}

	@Test
	public void testCalculateGameWithStrike() {
		int[] frame1 = CalculatingScore.createFrame(10, 0);
		int[] frame2 = CalculatingScore.createFrame(3, 6);
		int[] frame3 = CalculatingScore.createFrame(7, 2);
		int[] frame4 = CalculatingScore.createFrame(3, 6);
		int[] frame5 = CalculatingScore.createFrame(4, 4);
		int[] frame6 = CalculatingScore.createFrame(5, 3);
		int[] frame7 = CalculatingScore.createFrame(3, 3);
		int[] frame8 = CalculatingScore.createFrame(4, 5);
		int[] frame9 = CalculatingScore.createFrame(8, 1);
		int[] frame10 = CalculatingScore.createFrame(2, 6);

		int[] bonusThrows = new int[2];

		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);

		int gameScore = CalculatingScore.calculateGameScore(game);

		assertEquals(gameScore, 94);
	}

	@Test
	// Should return true and false since first is a spare and last is not
	public void testIsSpare() {
		int[] spare    = CalculatingScore.createFrame(6, 4);
		int[] notSpare = CalculatingScore.createFrame(5, 1);
		int[] strike   = CalculatingScore.createFrame(10, 0);

		boolean shouldBeSpare =  CalculatingScore.isSpare(spare);
		boolean shouldNotBeSpare =  CalculatingScore.isSpare(notSpare);
		boolean shouldNotBeSpare2 =  CalculatingScore.isSpare(strike);

		assertEquals(shouldBeSpare, true);
		assertEquals(shouldNotBeSpare, false);
		assertEquals(shouldNotBeSpare2, false);
	}

	@Test
	// Should return 15 since first is spare and frame after is [5, 1]
	public void testGetSpareScore() {
		int[] spare   		  = CalculatingScore.createFrame(6, 4);
		int[] frameAfterSpare = CalculatingScore.createFrame(5, 1);

		int score =  CalculatingScore.getSpareScore(spare, frameAfterSpare);

		assertEquals(score, 15);
	}

	@Test
	public void testcalculateStrikeFramePrecedeSpare() {
		int[] strike = CalculatingScore.createFrame(10, 0);
		int[] spare = CalculatingScore.createFrame(4,  6);
		int[] frame = CalculatingScore.createFrame(7, 2);

		int strikeScoreWithSpare = CalculatingScore.calculateStrike(strike, spare);
		assertEquals(20, strikeScoreWithSpare);	

		int strikeScoreWithoutSpare = CalculatingScore.calculateStrike(strike, frame);
		assertEquals(19, strikeScoreWithoutSpare);
	}

	@Test
	public void testCalculateGameWithStrikePrecedeSpare() {
		int[] frame1 = CalculatingScore.createFrame(10, 0);
		int[] frame2 = CalculatingScore.createFrame(4, 6);
		int[] frame3 = CalculatingScore.createFrame(7, 2);
		int[] frame4 = CalculatingScore.createFrame(3, 6);
		int[] frame5 = CalculatingScore.createFrame(4, 4);
		int[] frame6 = CalculatingScore.createFrame(5, 3);
		int[] frame7 = CalculatingScore.createFrame(3, 3);
		int[] frame8 = CalculatingScore.createFrame(4, 5);
		int[] frame9 = CalculatingScore.createFrame(8, 1);
		int[] frame10 = CalculatingScore.createFrame(2, 6);

		int[] bonusThrows = new int[2];

		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);

		int gameScore = CalculatingScore.calculateGameScore(game);

		assertEquals(103, gameScore);
	}
	

	@Test
	// Should return 88
	public void testGameContainingSpare() {
		int[] frame1 = CalculatingScore.createFrame(1, 9);
		int[] frame2 = CalculatingScore.createFrame(3, 6);
		int[] frame3 = CalculatingScore.createFrame(7, 2);
		int[] frame4 = CalculatingScore.createFrame(3, 6);
		int[] frame5 = CalculatingScore.createFrame(4, 4);
		int[] frame6 = CalculatingScore.createFrame(5, 3);
		int[] frame7 = CalculatingScore.createFrame(3, 3);
		int[] frame8 = CalculatingScore.createFrame(4, 5);
		int[] frame9 = CalculatingScore.createFrame(8, 1);
		int[] frame10 = CalculatingScore.createFrame(2, 6);

		int[] bonusThrows = new int[2];

		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);

		int gameScore = CalculatingScore.calculateGameScore(game);

		assertEquals(88, gameScore);
	}
	
	@Test
	// Should return 112
	public void testGameContainingMultipleStrikes() {
		int[] frame1 = CalculatingScore.createFrame(10, 0);
		int[] frame2 = CalculatingScore.createFrame(10, 0);
		int[] frame3 = CalculatingScore.createFrame(7, 2);
		int[] frame4 = CalculatingScore.createFrame(3, 6);
		int[] frame5 = CalculatingScore.createFrame(4, 4);
		int[] frame6 = CalculatingScore.createFrame(5, 3);
		int[] frame7 = CalculatingScore.createFrame(3, 3);
		int[] frame8 = CalculatingScore.createFrame(4, 5);
		int[] frame9 = CalculatingScore.createFrame(8, 1);
		int[] frame10 = CalculatingScore.createFrame(2, 6);

		int[] bonusThrows = new int[2];

		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);

		int gameScore = CalculatingScore.calculateGameScore(game);

		assertEquals(112, gameScore);
	}
	
	@Test
	// User story 10: Should return 98
	public void testGameContainingMultipleSpares() {
		int[] frame1 = CalculatingScore.createFrame(8, 2);
		int[] frame2 = CalculatingScore.createFrame(5, 5);
		int[] frame3 = CalculatingScore.createFrame(7, 2);
		int[] frame4 = CalculatingScore.createFrame(3, 6);
		int[] frame5 = CalculatingScore.createFrame(4, 4);
		int[] frame6 = CalculatingScore.createFrame(5, 3);
		int[] frame7 = CalculatingScore.createFrame(3, 3);
		int[] frame8 = CalculatingScore.createFrame(4, 5);
		int[] frame9 = CalculatingScore.createFrame(8, 1);
		int[] frame10 = CalculatingScore.createFrame(2, 6);

		int[] bonusThrows = new int[2];

		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);

		int gameScore = CalculatingScore.calculateGameScore(game);

		assertEquals(98, gameScore);
	}

	@Test
	// User story 10: Should return 90
	public void testGameLastFrameSpareWithBonusThrow() {
		int[] frame1 = CalculatingScore.createFrame(1, 5);
		int[] frame2 = CalculatingScore.createFrame(3, 6);
		int[] frame3 = CalculatingScore.createFrame(7, 2);
		int[] frame4 = CalculatingScore.createFrame(3, 6);
		int[] frame5 = CalculatingScore.createFrame(4, 4);
		int[] frame6 = CalculatingScore.createFrame(5, 3);
		int[] frame7 = CalculatingScore.createFrame(3, 3);
		int[] frame8 = CalculatingScore.createFrame(4, 5);
		int[] frame9 = CalculatingScore.createFrame(8, 1);
		int[] frame10 = CalculatingScore.createFrame(2, 8);
		
		int[] bonusThrows = new int[2];
		bonusThrows[0] = 7;

		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);

		int gameScore = CalculatingScore.calculateGameScore(game);

		assertEquals(90, gameScore);
	}
	
	@Test
	// User story 10: should return 17
	public void testScoreOfLastSpare() {
		int[] frame1 = CalculatingScore.createFrame(2, 8);
		int[] bonusThrows = {7};
		
		int spareScore =  CalculatingScore.getSpareScore(frame1, bonusThrows);

		assertEquals(17, spareScore);
	}

	@Test
	public void testcalculateStrikeLastFrame() {
		int[] strike = CalculatingScore.createFrame(10, 0);
		int[] bonusThrows = {7, 2};

		int frameScore = CalculatingScore.getStrikeLastFrameScore(strike, bonusThrows);

		assertEquals(19, frameScore);
	}

	@Test
	public void testCalculateGameWithStrikeLastFrame() {
		int[] frame1 = CalculatingScore.createFrame(1, 5);
		int[] frame2 = CalculatingScore.createFrame(3, 6);
		int[] frame3 = CalculatingScore.createFrame(7, 2);
		int[] frame4 = CalculatingScore.createFrame(3, 6);
		int[] frame5 = CalculatingScore.createFrame(4, 4);
		int[] frame6 = CalculatingScore.createFrame(5, 3);
		int[] frame7 = CalculatingScore.createFrame(3, 3);
		int[] frame8 = CalculatingScore.createFrame(4, 5);
		int[] frame9 = CalculatingScore.createFrame(8, 1);
		int[] frame10 = CalculatingScore.createFrame(10, 0);

		int[] bonusThrows = new int[2];
		bonusThrows[0] = 7;
		bonusThrows[1] = 2;

		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);

		int gameScore = CalculatingScore.calculateGameScore(game);

		assertEquals(92, gameScore);
	}
	
	
	@Test
	public void testCalculateGameLastFrameSpareAndBonusIsStrike() {
		int[] frame1 = CalculatingScore.createFrame(1, 5);
		int[] frame2 = CalculatingScore.createFrame(3, 6);
		int[] frame3 = CalculatingScore.createFrame(7, 2);
		int[] frame4 = CalculatingScore.createFrame(3, 6);
		int[] frame5 = CalculatingScore.createFrame(4, 4);
		int[] frame6 = CalculatingScore.createFrame(5, 3);
		int[] frame7 = CalculatingScore.createFrame(3, 3);
		int[] frame8 = CalculatingScore.createFrame(4, 5);
		int[] frame9 = CalculatingScore.createFrame(8, 1);
		int[] frame10 = CalculatingScore.createFrame(2, 8);
		
		int[] bonusThrows = new int[2];
		bonusThrows[0] = 9;
		bonusThrows[1] = 1;
		
		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);
		
		int gameScore = CalculatingScore.calculateGameScore(game);
		
		assertEquals(93, gameScore);
	}
	
	@Test
	public void testCalculateGameWithPerfectGame() {
		int[] frame1 = CalculatingScore.createFrame(10, 0);
		int[] frame2 = CalculatingScore.createFrame(10, 0);
		int[] frame3 = CalculatingScore.createFrame(10, 0);
		int[] frame4 = CalculatingScore.createFrame(10, 0);
		int[] frame5 = CalculatingScore.createFrame(10, 0);
		int[] frame6 = CalculatingScore.createFrame(10, 0);
		int[] frame7 = CalculatingScore.createFrame(10, 0);
		int[] frame8 = CalculatingScore.createFrame(10, 0);
		int[] frame9 = CalculatingScore.createFrame(10, 0);
		int[] frame10 = CalculatingScore.createFrame(10, 0);
		
		int[] bonusThrows = new int[2];
		bonusThrows[0] = 10;
		bonusThrows[1] = 10;
		
		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);
		
		int gameScore = CalculatingScore.calculateGameScore(game);

		assertEquals(300, gameScore);
	}
	
	@Test
	public void testCalculateGameWithRealGame() {
		int[] frame1 = CalculatingScore.createFrame(6, 3);
		int[] frame2 = CalculatingScore.createFrame(7, 1);
		int[] frame3 = CalculatingScore.createFrame(8, 2);
		int[] frame4 = CalculatingScore.createFrame(7, 2);
		int[] frame5 = CalculatingScore.createFrame(10, 0);
		int[] frame6 = CalculatingScore.createFrame(6, 2);
		int[] frame7 = CalculatingScore.createFrame(7, 3);
		int[] frame8 = CalculatingScore.createFrame(10, 0);
		int[] frame9 = CalculatingScore.createFrame(8, 0);
		int[] frame10 = CalculatingScore.createFrame(7, 3);
		
		int[] bonusThrows = new int[2];
		bonusThrows[0] = 10;
		
		int[][] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, bonusThrows);
		
		int gameScore = CalculatingScore.calculateGameScore(game);

		assertEquals(135, gameScore);
	}
}
	
