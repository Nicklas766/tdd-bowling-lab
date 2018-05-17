package bowling;
import java.util.stream.*;

public class CalculatingScore {
	public int[] createFrame(int throw1, int throw2) throws IllegalArgumentException {
		if (throw1 < 0 || throw1 > 10 || throw2 < 0 || throw2 > 10) {
			throw new IllegalArgumentException("A throw has to be between 0 and 10");
		}
		if ((throw1 + throw2) > 10) {
			throw new IllegalArgumentException("The two throws can not be more than 10 combined");
		}
		int[] frame = {throw1, throw2};
		return frame;
	}

	public Object[] createGame(int[] frame1, int[] frame2, int[] frame3, int[] frame4, int[] frame5, int[] frame6, int[] frame7, int[] frame8, int[] frame9, int[] frame10) {
		Object[] game = new Object[10];
		game[0] = frame1;
		game[1] = frame2;
		game[2] = frame3;
		game[3] = frame4;
		game[4] = frame5;
		game[5] = frame6;
		game[6] = frame7;
		game[7] = frame8;
		game[8] = frame9;
		game[9] = frame10;

		return game;
	}
	
	public static int getFrameScore(int[] frame) {
		return IntStream.of(frame).sum();
	}
}
