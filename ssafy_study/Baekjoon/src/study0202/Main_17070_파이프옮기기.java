package study0202;

import java.util.Scanner;

public class Main_17070_파이프옮기기 {

	static int N, cnt;
	static int[][] home;
	static int[] dy = { 0, 1, 1 };
	static int[] dx = { 1, 1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		home = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				home[i][j] = sc.nextInt();
			}
		}
		dfs(0, 1, 0);
		System.out.println(cnt);

	}

	public static void dfs(int y, int x, int d) {
		if (y == N - 1 && x == N - 1) {
			cnt++;
			return;
		}
		for (int i = 0; i < 3; i++) {
			if (d == 0) { // 가로
				if (i == 2)
					continue;
			} else if (d == 2) {// 세로
				if (i == 0)
					continue;
			}
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny >= N || nx >= N || home[ny][nx] == 1)
				continue;
			if (i == 1) {
				if (home[ny - 1][nx] == 1 || home[ny][nx - 1] == 1)
					continue;
			}
			dfs(ny, nx, i);
		}
	}

}
