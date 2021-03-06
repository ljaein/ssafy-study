package study0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14442_벽부수고이동하기2 {

	static int N, M, K, ans;
	static int[][] map;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = c[j] - '0';
			}
		}
		bfs();
		System.out.println(ans == 0 ? -1 : ans);
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		int[][][] visit = new int[N][M][11];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(visit[i][j], Integer.MAX_VALUE);
			}
		}
		visit[0][0][0] = 0;
		q.add(new Point(0, 0, 0, 1));
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.y == N - 1 && cur.x == M - 1) {
				ans = cur.cnt;
				break;
			}
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					continue;
				if (map[ny][nx] == 0) {
					if (visit[ny][nx][cur.w] <= cur.cnt + 1)
						continue;
					visit[ny][nx][cur.w] = cur.cnt + 1;
					q.add(new Point(ny, nx, cur.w, cur.cnt + 1));
				} else if (map[ny][nx] == 1) {
					if (cur.w >= K)
						continue;
					if (visit[ny][nx][cur.w + 1] <= cur.cnt + 1)
						continue;
					visit[ny][nx][cur.w + 1] = cur.cnt + 1;
					q.add(new Point(ny, nx, cur.w + 1, cur.cnt + 1));
				}
			}
		}
	}

	static class Point {
		int y, x, w, cnt;

		public Point(int y, int x, int w, int cnt) {
			this.y = y;
			this.x = x;
			this.w = w;
			this.cnt = cnt;
		}

	}
}
