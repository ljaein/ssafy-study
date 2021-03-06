package study0202;

import java.util.Scanner;

public class Solution_D5_7206_숫자게임 {

	static int T;
	static String num;
	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			num = sc.next();
			max = 0;
			dfs(num, 0);
			System.out.println("#" + t + " " + max);
		}
	}

	public static void dfs(String n, int cnt) {
		if (Integer.parseInt(n) < 10) {
			max = Math.max(max, cnt);
			return;
		}
		for (int i = 1; i < n.length(); i++) {
			for (int j = i + 1; j <= n.length(); j++) {
				int a = Integer.parseInt(n.substring(0, i));
				int b = Integer.parseInt(n.substring(i, j));
				if (j != n.length()) {
					int c = Integer.parseInt(n.substring(j, n.length()));
					dfs(String.valueOf(a * b * c), cnt + 1);
				} else
					dfs(String.valueOf(a * b), cnt + 1);
			}
		}
	}

}
