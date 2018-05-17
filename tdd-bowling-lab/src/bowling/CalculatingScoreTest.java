package bowling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatingScoreTest {

	@Test
	public void addFrame() {
		int throw1 = 5;
		int throw2 = 4;

		CalculatingScore scorer = new CalculatingScore();
		int[] frame = scorer.createFrame(throw1, throw2);

		int[] expectedResult = new int[] {5, 4};

		assertArrayEquals(expectedResult, frame);
	}

}
