package bowling;
import java.util.stream.*;

public class CalculatingScore {
	public static int[] createFrame(int throw1, int throw2) throws IllegalArgumentException {
		if (throw1 < 0 || throw1 > 10 || throw2 < 0 || throw2 > 10) {
			throw new IllegalArgumentException("A throw has to be between 0 and 10");
		}
		if ((throw1 + throw2) > 10) {
			throw new IllegalArgumentException("The two throws can not be more than 10 combined");
		}
		int[] frame = {throw1, throw2};
		return frame;
	}

	public static Object[] createGame(int[] frame1, int[] frame2, int[] frame3, int[] frame4, int[] frame5, int[] frame6, int[] frame7, int[] frame8, int[] frame9, int[] frame10) {
		Object[] game = {frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10};

		return game;
	}
	
	public static int getFrameScore(int[] frame) {
		return IntStream.of(frame).sum();
	}
	
	public static boolean isSpare(int[] frame) {
		return (getFrameScore(frame) == 10);
	}

	public static boolean isStrike(int[] frame) throws IllegalArgumentException {
		return frame[0] == 10;
	}

	public static int calculateStrike(int[] strikeFrame, int[] afterStrikeFrame) {
		return getFrameScore(strikeFrame) + getFrameScore(afterStrikeFrame);
	}
}
