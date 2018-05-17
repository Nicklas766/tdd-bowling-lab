package bowling;

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

	public Object[] createGame() {
		int[] frame = new int[2];
		Object[] game = {frame, frame, frame, frame, frame, frame, frame, frame, frame, frame};

		return game;
	}
}
