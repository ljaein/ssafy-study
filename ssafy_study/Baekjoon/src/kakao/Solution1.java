package kakao;

public class Solution1 {

	public static void main(String[] args) {
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		String hand = "right";
		System.out.println(solution(numbers, hand));

	}

	public static String solution(int[] numbers, String hand) {
		String answer = "";
		StringBuffer sb = new StringBuffer();
		Hand lh = new Hand(3, 0);
		Hand rh = new Hand(3, 2);
		int len = numbers.length;
		for (int i = 0; i < len; i++) {
			if (numbers[i] == 1) {
				sb.append("L");
				lh.y = 0;
				lh.x = 0;
			} else if (numbers[i] == 4 || numbers[i] == 7) {
				sb.append("L");
				lh.y = (numbers[i] - 1) / 3;
				lh.x = 0;
			} else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				sb.append("R");
				rh.y = numbers[i] / 3 - 1;
				rh.x = 2;
			} else {
				int yidx = 0;
				if (numbers[i] == 2) {
					yidx = 0;
				} else if (numbers[i] == 5) {
					yidx = 1;
				} else if (numbers[i] == 8) {
					yidx = 2;
				} else {
					yidx = 3;
				}
				int ldis = Math.abs(lh.y - yidx) + Math.abs(lh.x - 1);
				int rdis = Math.abs(rh.y - yidx) + Math.abs(rh.x - 1);
				if (ldis < rdis) {
					sb.append("L");
					lh.y=yidx;
					lh.x=1;
				} else if (ldis > rdis) {
					sb.append("R");
					rh.y=yidx;
					rh.x=1;
				} else {
					if(hand.equals("right")){
						sb.append("R");
						rh.y=yidx;
						rh.x=1;
					}else{
						sb.append("L");
						lh.y=yidx;
						lh.x=1;
					}
				}
			}
		}
		answer = sb.toString();
		return answer;
	}

	static class Hand {
		int y, x;

		public Hand(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
