package bowling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatingScoreTest {

	@Test
	public void addFrameCorrectFrame() {
		int throw1 = 5;
		int throw2 = 4;

		CalculatingScore scorer = new CalculatingScore();
		int[] frame = scorer.createFrame(throw1, throw2);

		int[] expectedResult = new int[] {5, 4};

		assertArrayEquals(expectedResult, frame);
	}

	@Test
	public void addFrameIncorrectInputs() {
		int throw1 = -1;
		int throw2 = 4;

		CalculatingScore scorer = new CalculatingScore();

		Throwable exception = assertThrows(IllegalArgumentException.class, ()->{scorer.createFrame(throw1, throw2);} );
		assertEquals("A throw has to be between 0 and 10", exception.getMessage());
	}

	@Test
	public void addFrameIncorrectInputSum() {
		int throw1 = 5;
		int throw2 = 8;

		CalculatingScore scorer = new CalculatingScore();

		Throwable exception = assertThrows(IllegalArgumentException.class, ()->{scorer.createFrame(throw1, throw2);} );
		assertEquals("The two throws can not be more than 10 combined", exception.getMessage());
	}

	@Test
	public void createGame() {
		CalculatingScore scorer = new CalculatingScore();
		Object[] game = scorer.createGame();

		int[] frame = new int[2];
		Object[] expectedResult = {frame, frame, frame, frame, frame, frame, frame, frame, frame, frame};

		assertArrayEquals(expectedResult, game);
	}
	
	
	@Test
	// Should return sum of frame, which is 9 in this case
	public void testGetFrameScore() {
		int [] frame   = new CalculatingScore().createFrame(5, 4);
		int frameScore = new CalculatingScore().getFrameScore(frame);
		
		assertEquals(frameScore, 9);
	}
}
