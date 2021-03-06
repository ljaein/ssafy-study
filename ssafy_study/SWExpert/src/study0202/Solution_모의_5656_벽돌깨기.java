package study0202;

import java.util.Scanner;

public class Solution_모의_5656_벽돌깨기 {

	static int W, H, N, T, res, min;
	static int[][] map;
	static int[] ball;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt(); // 떨어뜨리는 구슬의 개수
			W = sc.nextInt(); // 가로
			H = sc.nextInt(); // 세로
			min = Integer.MAX_VALUE;
			map = new int[H][W];
			ball = new int[N];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			comb(0, 0);
			System.out.println("#" + t + " " + min);
		}

	}

	public static void comb(int start, int cnt) {
		if (cnt == N) {
			shoot(ball, map);
			return;
		}
		for (int i = 0; i < W; i++) {
			ball[cnt] = i;
			comb(i, cnt + 1);
		}
	}

	public static void shoot(int[] ba, int[][] map) {
		int[][] m = new int[H][W];
		for (int i = 0; i < H; i++) {
			System.arraycopy(map[i], 0, m[i], 0, W);
		}
		for (int b = 0; b < ba.length; b++) { // 구슬마다 실행
			for (int i = 0; i < H; i++) {
				if (m[i][ba[b]] != 0) {
					bfs(i, ba[b], m);
					delete(m);
					break;
				}
			}
		}
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (m[i][j] != 0)
					res++;
			}
		}
		min = Math.min(min, res);
		res = 0;
	}

	public static void bfs(int y, int x, int[][] m) {
		int num = m[y][x];
		m[y][x] = 0;
		if (num == 1 || num == 0)
			return;
		for (int i = 0; i < 4; i++) {
			int ny = y;
			int nx = x;
			for (int n = 0; n < num - 1; n++) {
				ny = ny + dy[i];
				nx = nx + dx[i];
				if (ny < 0 || nx < 0 || ny >= H || nx >= W)
					continue;
				bfs(ny, nx, m);
			}
		}
	}

	public static void delete(int[][] m) {
		for (int j = 0; j < W; j++) {
			int idx = H - 1;
			for (int i = H - 1; i >= 0; i--) {
				if (m[i][j] != 0) {
					m[idx][j] = m[i][j];
					idx--;
				}
			}
			for (int k = 0; k <= idx; k++) {
				m[k][j] = 0;
			}
		}
	}
}
