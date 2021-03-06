package study0304;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_16234_인구이동 {

	static int N, L, R, ans;
	static boolean move;
	static int[][] map;
	static boolean[][] visit;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		move = true;
		while (move) {
			int sum = 0;
			move = false;
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						bfs(i, j, sum);
					}
				}
			}
			if(!move)
				break;
			ans++;
		}
		System.out.println(ans);
	}

	static void bfs(int y, int x, int sum) {
		boolean islink = false;
		Queue<Point> q = new LinkedList<>();
		LinkedList<Point> list = new LinkedList<>();
		visit[y][x] = true;
		q.add(new Point(y, x));
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx])
					continue;
				int sub = Math.abs(map[cur.y][cur.x] - map[ny][nx]);
				if (sub >= L && sub <= R) {
					islink = true;
					visit[ny][nx] = true;
					q.add(new Point(ny, nx));
					list.add(new Point(ny, nx));
					sum += map[ny][nx];
				}
			}
		}
		if (islink) {
			sum += map[y][x];
			list.add(new Point(y, x));
			int avg = sum / list.size();
			for (Point p : list) {
				map[p.y][p.x] = avg;
			}
			move = true;
		}
	}

	static class Point {
		int y, x;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}
}
