package study0404;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1938_통나무옮기기 {

	static int N;
	static boolean flag;
	static char[][] map;
	static boolean[][][] visit;
	static Point[] tr = new Point[3];
	static Point[] etr = new Point[3];
	static int[] dy = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dx = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new char[N][N];
		visit = new boolean[N][N][2];
		int idx = 0;
		int idx2 = 0;
		for (int i = 0; i < N; i++) {
			map[i] = sc.next().toCharArray();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B')
					tr[idx++] = new Point(i, j, 0, 0);
				else if (map[i][j] == 'E')
					etr[idx2++] = new Point(i, j, 0, 0);
			}
		}
		bfs();
		if(!flag)
			System.out.println(0);
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		int sh, esh;
		if (tr[0].y == tr[1].y) {
			sh = 1;
		} else
			sh = 0;
		if (etr[0].y == etr[1].y) {
			esh = 1;
		} else
			esh = 0;
		int ey = etr[1].y;
		int ex = etr[1].x;
		q.add(new Point(tr[1].y, tr[1].x, sh, 0));
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.y == ey && cur.x == ex && cur.s == esh) {
				flag = true;
				System.out.println(cur.cnt);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx][cur.s] || map[ny][nx] == '1')
					continue;
				if (cur.s == 0) {
					if (ny + 1 < 0 || ny + 1 >= N || ny - 1 < 0 || ny - 1 >= N || map[ny + 1][nx] == '1'
							|| map[ny - 1][nx] == '1')
						continue;
				} else {
					if (nx + 1 < 0 || nx + 1 >= N || nx - 1 < 0 || nx - 1 >= N || map[ny][nx + 1] == '1'
							|| map[ny][nx - 1] == '1')
						continue;
				}
				visit[ny][nx][cur.s] = true;
				q.add(new Point(ny, nx, cur.s, cur.cnt + 1));
			}
			if (visit[cur.y][cur.x][cur.s == 1 ? 0 : 1])
				continue;
			// 회전할때 안걸리면
			boolean rot = true;
			for (int d = 0; d < 8; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == '1') {
					rot = false;
					break;
				}
			}
			if (rot) {
				visit[cur.y][cur.x][cur.s == 1 ? 0 : 1] = true;
				q.add(new Point(cur.y, cur.x, cur.s == 1 ? 0 : 1, cur.cnt + 1));
			}

		}

	}

	static class Point {
		int y, x, s, cnt;

		public Point(int y, int x, int s, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.s = s;
			this.cnt = cnt;
		}
	}
}
