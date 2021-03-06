package study0301;

import java.util.Scanner;

public class Solution_D4_4796_의석이의우뚝선산 {

	static int N, ans;
	static int[] san;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			ans = 0;
			N = sc.nextInt();
			san = new int[N];
			for (int i = 0; i < N; i++) {
				san[i] = sc.nextInt();
			}
			int l = 0;
			int r = 0;
			for (int i = 0; i < N - 1; i++) {
				if (i != 0 && san[i - 1] > san[i] && san[i] < san[i + 1]) {
					ans += l * r;
					l = 0;
					r = 0;
				}
				if (i == N - 2 && san[i] > san[i + 1]) {
					ans += l * (r + 1);
					l = 0;
					r = 0;
				}
				if (san[i] < san[i + 1])
					l++;
				if (san[i] > san[i + 1])
					r++;

			}
			System.out.println("#" + t + " " + ans);

		}
	}

}
