package study0301;

import java.util.Scanner;

public class Solution_D4_5684_운동 {

	static int N, M, ans;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			ans = Integer.MAX_VALUE;
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N + 1][N + 1];
			for (int i = 0; i < M; i++) {
				int s = sc.nextInt();
				int e = sc.nextInt();
				int c = sc.nextInt();
				map[s][e] = c;
			}
			for (int i = 1; i <= N; i++) {
				visited = new boolean[N + 1];
				dfs(i, i, 0);
			}
			System.out.println("#" + t + " " + (ans == Integer.MAX_VALUE ? -1 : ans));

		}
	}

	static void dfs(int start, int v, int c) {
		if (start == v && visited[v]) {
			if (ans > c) {
				ans = c;
			}
			return;
		}
		if (visited[v])
			return;
		if (c > ans)
			return;
		visited[v] = true;
		for (int i = 1; i <= N; i++) {
			if (map[v][i] != 0) {
				dfs(start, i, c + map[v][i]);
			}
		}
	}

}
