package study0304;

import java.util.Scanner;

public class Solution_D4_7194_화섭이의미생물배양 {

	static int T, s, t, a, b;
	static int day, min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			s = sc.nextInt();
			t = sc.nextInt();
			a = sc.nextInt();
			b = sc.nextInt();
			day = 0;
			min = Integer.MAX_VALUE;
			if (b == 1) {
				if ((t - s) % a == 0) {
					min = (t - s) / a;
				} else {
					min = Integer.MAX_VALUE;
				}
			} else {
				dfs(t, 0);
			}
			System.out.println("#" + tc + " " + (min == Integer.MAX_VALUE ? -1 : min));

		}

	}

	public static void dfs(int cnt, int day) {
		if (cnt == s) {
			min = Math.min(min, day);
			return;
		}
		if (cnt < s)
			return;
		if (cnt % b == 0) {
			if (cnt / b < s)
				dfs(cnt - a, day + 1);
			else
				dfs(cnt / b, day + 1);
		} else
			dfs(cnt - a, day + 1);

	}
}
