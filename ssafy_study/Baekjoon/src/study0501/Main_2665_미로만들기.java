package study0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2665_미로만들기 {

	static int N, ans;
	static int[][] map;
	static int[][] visit;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visit[i][j]=Integer.MAX_VALUE;
			}
		}
		ans = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = c[j] - '0';
			}
		}
		bfs();
		System.out.println(ans);

	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		visit[0][0] = 0;
		q.add(new Point(0, 0, 0));
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.y == N - 1 && cur.x == N - 1) {
				ans = Math.min(ans, cur.b);
				continue;
			}
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] <= cur.b + 1)
					continue;
				visit[ny][nx] = cur.b + 1;
				if (map[ny][nx] == 0) {
					q.add(new Point(ny, nx, cur.b + 1));
				} else if (map[ny][nx] == 1) {
					q.add(new Point(ny, nx, cur.b));
				}
			}
		}
	}

	static class Point {
		int y, x, b;

		public Point(int y, int x, int b) {
			super();
			this.y = y;
			this.x = x;
			this.b = b;
		}


	}
}
