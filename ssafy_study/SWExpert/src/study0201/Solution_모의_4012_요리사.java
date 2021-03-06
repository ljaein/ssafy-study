package study0201;

import java.util.Scanner;

public class Solution_모의_4012_요리사 {

	static int T, N, min;
	static int[][] food;
	static int[] A, B;
	static boolean[] chk;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			A = new int[N / 2];
			B = new int[N / 2];

			food = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					food[i][j] = sc.nextInt();
				}
			}
			min = Integer.MAX_VALUE;
			Comb(0, 0);
			System.out.println("#" + t + " " + min);
		}

	}

	public static void Comb(int start, int cnt) {
		if (cnt == N / 2) {
			chk = new boolean[N];
			for (int i = 0; i < N / 2; i++) {
				chk[A[i]] = true;
			}
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if (!chk[i])
					B[idx++] = i;
			}
			calc(A, B);
			return;
		}
		for (int i = start; i < N; i++) {
			A[cnt] = i;
			Comb(i + 1, cnt + 1);
		}
	}

	public static void calc(int[] a, int[] b) {
		int asum = 0;
		int bsum = 0;
		for (int i = 0; i < N / 2; i++) {
			for (int j = i + 1; j < N / 2; j++) {
				asum += food[a[i]][a[j]]+ food[a[j]][a[i]];
				bsum += food[b[i]][b[j]]+ food[b[j]][b[i]];
			}
		}
		int cha = Math.abs(asum - bsum);
		if (min > cha) {
			min = cha;
		}
	}

}
