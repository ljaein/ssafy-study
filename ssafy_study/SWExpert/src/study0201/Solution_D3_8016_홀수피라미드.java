package study0201;

import java.util.Scanner;

public class Solution_D3_8016_Ȧ���Ƕ�̵� {

	static long T, N;
	static long first, last;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			first = 2 * (N-1) * (N-1) +1;
			last = 2 * N * N-1;
			//first = 2 * (long) Math.pow(N - 1, 2) + 1;
			//last = 2 * (long) Math.pow(N, 2) - 1;

			// long Ncnt = N * 2 - 1;
			// long lidx = 0;
			// for (int i = 0; i < N; i++) {
			// lidx += i * 2 + 1;
			// }
			// long fidx = lidx - Ncnt + 1;
			// first = 2 * fidx - 1;
			// last = 2 * lidx - 1;

			System.out.println("#" + t + " " + first + " " + last);
		}

	}
}
