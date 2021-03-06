package study0304;

import java.util.Scanner;

public class Main_15650_N과M2 {

	static int N, M;
	static int[] sel;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sel = new int[M];
		Combi(0,0);
		System.out.println(sb.toString());
	}

	static void Combi(int start, int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++)
				sb.append(sel[i]).append(" ");
			sb.append("\n");
			return;
		}
		for (int i = start; i < N; i++) {
			sel[cnt] = i + 1;
			Combi(i + 1, cnt + 1);
		}
	}
}
