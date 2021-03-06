package study0204;

import java.util.Scanner;

public class Solution_D4_7208_지도칠하기 {

	static int T, N, min;
	static int[] fix_C, C, sel;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			fix_C = new int[N];
			C = new int[N];
			sel = new int[N];
			min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				fix_C[i] = sc.nextInt();
			}
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			perm(0, 0);
			System.out.println("#" + t + " " + min);
		}
	}

	public static void perm(int flag, int cnt) {
		if (cnt == N) {
			int res = 0;
			System.arraycopy(fix_C, 0, C, 0, N);
			isLink();
			if (check()) {
				for (int i = 0; i < N; i++) {
					if (fix_C[i] != C[i])
						res++;
				}
				min = Math.min(min, res);
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) == 0) {
				sel[cnt] = i;
				perm(flag | 1 << i, cnt + 1);
			}
		}
	}

	public static void isLink() {
		for (int i = 0; i < N; i++) {
			boolean[] selC = new boolean[4];
			boolean chk = false;
			for (int j = 0; j < N; j++) {
				if (map[sel[i]][j] == 1) {
					selC[C[j] - 1] = true;
					if (C[sel[i]] == C[j])
						chk = true;
				}
			}
			if (chk) {
				for (int j = 0; j < 4; j++) {
					if (!selC[j]) {
						C[sel[i]] = j + 1;
						break;
					}
				}
			}
		}

	}

	public static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[sel[i]][j] == 1) {
					if (C[sel[i]] == C[j])
						return false;
				}
			}
		}
		return true;
	}
}
