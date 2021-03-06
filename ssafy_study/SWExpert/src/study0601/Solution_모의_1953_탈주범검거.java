package study0601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_모의_1953_탈주범검거 {

	static int T, N, M, R, C, L, ans;
	static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static boolean[][] link = { { true, true, true, true }, { true, true, false, false }, { false, false, true, true },
			{ true, false, false, true }, { false, true, false, true }, { false, true, true, false },
			{ true, false, true, false } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			ans = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs(R, C);
			System.out.println("#" + t + " " + ans);
		}
	}

	static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		visit[r][c] = true;
		q.add(new Point(r, c, 1));
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.cnt > L) {
				return;
			}
			ans++;
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] == 0)
					continue;
				if (!link[map[cur.y][cur.x] - 1][d] || !link[map[ny][nx] - 1][getD(d)])
					continue;
				visit[ny][nx] = true;
				q.add(new Point(ny, nx, cur.cnt + 1));
			}
		}
	}

	static int getD(int d) {
		if (d == 0)
			return 1;
		else if (d == 1)
			return 0;
		else if (d == 2)
			return 3;
		else
			return 2;
	}

	static class Point {
		int y, x, cnt;

		public Point(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}
