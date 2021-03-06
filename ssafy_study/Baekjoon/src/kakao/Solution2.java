package kakao;

import java.util.LinkedList;

public class Solution2 {

	public static void main(String[] args) {
		String expression = "50*6-3*2";
		System.out.println(solution(expression));

	}

	public static long solution(String expression) {
		long answer = 0;
		char[][] oper = { { '+', '-', '*' }, { '+', '*', '-' }, { '-', '+', '*' }, { '-', '*', '+' }, { '*', '+', '-' },
				{ '*', '-', '+' } };
		LinkedList<Long> f_nlist = new LinkedList<>();
		LinkedList<Character> f_olist = new LinkedList<>();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '-' || expression.charAt(i) == '+' || expression.charAt(i) == '*') {
				f_nlist.add(Long.parseLong(sb.toString()));
				sb = new StringBuffer();
				f_olist.add(expression.charAt(i));
			} else {
				sb.append(expression.charAt(i));
			}
		}
		f_nlist.add(Long.parseLong(sb.toString()));

		LinkedList<Long> nlist = new LinkedList<>();
		LinkedList<Character> olist = new LinkedList<>();
		for (int i = 0; i < 6; i++) {
			nlist = (LinkedList<Long>)f_nlist.clone();
			olist = (LinkedList<Character>)f_olist.clone();
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < olist.size(); k++) {
					if (olist.get(k) == oper[i][j]) {
						long a = nlist.get(k);
						long b = nlist.get(k + 1);
						nlist.remove(k);
						nlist.remove(k);
						if (oper[i][j] == '*') {
							nlist.add(k, a * b);
						} else if (oper[i][j] == '+') {
							nlist.add(k, a + b);
						} else {
							nlist.add(k, a - b);
						}
						olist.remove(k);
						k--;
					}
				}
			}
			answer = Math.max(answer, Math.abs(nlist.get(0)));
		}
		return answer;
	}

}
