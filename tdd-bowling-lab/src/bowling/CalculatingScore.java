package bowling;

public class CalculatingScore {
	public int[] createFrame(int throw1, int throw2) throws Exception {
		if (throw1 < 0 || throw1 > 10 || throw2 < 0 || throw2 > 10) {
			throw new Exception("A throw has to be between 0 and 10");
		}
		if ((throw1 + throw2) > 10) {
			throw new Exception("The two throws can not be more than 10 combined");
		}
		int[] frame = new int[2];
		frame[0] = throw1;
		frame[1] = throw2;
		return frame;
	}
}
