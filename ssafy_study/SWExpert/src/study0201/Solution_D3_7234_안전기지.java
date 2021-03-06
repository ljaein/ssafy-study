package study0201;

import java.util.Scanner;

public class Solution_D3_7234_안전기지{

	static int T, N, B, max;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			max = 0;
			N = sc.nextInt();
			B = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < B; i++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				start(y, x);

			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max < map[i][j]) {
						max = map[i][j];
					}
				}
			}
			System.out.println("#" + t + " " + max);

		}
	}

	public static void start(int y, int x) {
		int[] dy = { -1, -2, 0, 0, 1, 2, 0, 0 };
		int[] dx = { 0, 0, 1, 2, 0, 0, -1, -2 };
		int cury = y;
		int curx = x;
		for (int d = 0; d < 8; d++) {
			int ny = cury + dy[d];
			int nx = curx + dx[d];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N)
				continue;
			if (map[ny][nx] == -1)
				continue;
			map[ny][nx]++;
		}
	}

}
