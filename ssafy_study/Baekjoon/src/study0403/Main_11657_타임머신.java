package study0403;

import java.util.Arrays;
import java.util.Scanner;

public class Main_11657_타임머신 {

	static int N, M;
	static Edge[] T;
	static long[] dist;
	static StringBuilder sb = new StringBuilder();
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = new Edge[M];
		dist = new long[N];
		Arrays.fill(dist, INF);
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int c = sc.nextInt();
			T[i] = new Edge(a, b, c);
		}
		solve();

	}

	static void solve() {
		dist[0] = 0;
		for (int i = 0; i < N - 1; i++) { // 정점-1 만큼 반복
			for (int j = 0; j < M; j++) {
				Edge cur = T[j];
				if (dist[cur.a] != INF && dist[cur.b] > dist[cur.a] + cur.c) {
					dist[cur.b] = dist[cur.a] + cur.c;
				}
			}
		}
		// 음수사이클 확인

		for (int j = 0; j < M; j++) {
			Edge cur = T[j];
			if (dist[cur.a] != INF && dist[cur.b] > dist[cur.a] + cur.c) { // 갱신되는 곳이 있으면 음수사이클
				System.out.println(-1);
				return;
			}
		}
		for (int i = 1; i < N; i++) {
			if (dist[i] == INF)
				sb.append("-1\n");
			else
				sb.append(dist[i] + "\n");
		}
		System.out.println(sb.toString());
	}

	static class Edge {
		int a, b, c;

		public Edge(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

}
