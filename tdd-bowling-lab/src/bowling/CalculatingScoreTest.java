package bowling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatingScoreTest {

	@Test
	public void addFrameCorrectFrame() {
		int throw1 = 5;
		int throw2 = 4;

		int[] frame = CalculatingScore.createFrame(throw1, throw2);

		int[] expectedResult = new int[] {5, 4};

		assertArrayEquals(expectedResult, frame);
	}

	@Test
	public void addFrameIncorrectInputs() {
		int throw1 = -1;
		int throw2 = 4;

		Throwable exception = assertThrows(IllegalArgumentException.class, ()->{CalculatingScore.createFrame(throw1, throw2);} );
		assertEquals("A throw has to be between 0 and 10", exception.getMessage());
	}

	@Test
	public void addFrameIncorrectInputSum() {
		int throw1 = 5;
		int throw2 = 8;

		Throwable exception = assertThrows(IllegalArgumentException.class, ()->{CalculatingScore.createFrame(throw1, throw2);} );
		assertEquals("The two throws can not be more than 10 combined", exception.getMessage());
	}

	@Test
	public void createGame() {
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

		Object[] game = CalculatingScore.createGame(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10);

		Object[] expectedResult = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10};

		assertArrayEquals(expectedResult, game);
	}
	
	
	@Test
	// Should return sum of frame, which is 9 in this case
	public void testGetFrameScore() {
		int[] frame    = CalculatingScore.createFrame(5, 4);
		int frameScore = CalculatingScore.getFrameScore(frame);
		
		assertEquals(frameScore, 9);
	}
}
