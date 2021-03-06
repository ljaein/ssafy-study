package study0404;

import java.util.Scanner;

public class Main_12100_2048 {

	static int N, ans;
	static int[][] fmap, map;
	static int[] sel;
	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		fmap = new int[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				fmap[i][j] = sc.nextInt();
			}
		}
		sel = new int[5];
		combi(0);
		System.out.println(ans);
	}

	static void combi(int cnt) {
		if (cnt == 5) {
			for (int i = 0; i < N; i++)
				System.arraycopy(fmap[i], 0, map[i], 0, N);
			for (int i = 0; i < 5; i++)
				move(sel[i]);
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, map[i][j]);
				}
			}
			ans = Math.max(ans, max);
			return;
		}
		for (int i = 0; i < 4; i++) {
			sel[cnt] = i;
			combi(cnt + 1);
		}
	}

	static void move(int dir) {
		if (dir == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					push(i, j, 0, i, j);
			}
		} else if (dir == 0) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--)
					push(i, j, 1, i, j);
			}
		} else if (dir == 3) {
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++)
					push(i, j, 2, i, j);
			}
		} else if (dir == 2) {
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i >= 0; i--)
					push(i, j, 3, i, j);
			}
		}
	}

	static void push(int y, int x, int d, int cy, int cx) {
		int ny = y + dy[d];
		int nx = x + dx[d];
		if (ny < 0 || nx < 0 || ny >= N || nx >= N)
			return;
		if (map[ny][nx] == 0) {
			push(ny, nx, d, cy, cx);
		} else if (map[cy][cx] != map[ny][nx]) {
			if (map[cy][cx] == 0) {
				map[cy][cx] = map[ny][nx];
				map[ny][nx] = 0;
				push(cy, cx, d, cy, cx);
			}
			return;
		} else if (map[ny][nx] == map[cy][cx]) {
			map[cy][cx] *= 2;
			map[ny][nx] = 0;
			return;
		}
	}

}
