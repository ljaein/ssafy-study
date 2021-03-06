package study0204;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_17472_다리만들기2 {

	static int N, M, num, res;
	static int[][] map, link;
	static boolean[][] visited;
	static PriorityQueue<Point> pq;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					num++;
					map[i][j] = num;
					sumNum(i, j, num);
				}
			}
		}

		link = new int[num + 1][num + 1];
		for (int i = 0; i < num + 1; i++) {
			for (int j = 0; j < num + 1; j++) {
				link[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					bridge(i, j, map[i][j]);
				}
			}
		}

		pq = new PriorityQueue<>();
		for (int i = 0; i < num + 1; i++) {
			for (int j = i + 1; j < num + 1; j++) {
				if (link[i][j] != Integer.MAX_VALUE)
					pq.add(new Point(i, j, link[i][j]));
			}
		}
		mst();
		System.out.println(res);

	}

	public static void mst() {
		int cnt = 0;
		int[] ver = new int[num + 1];
		for (int i = 1; i < num + 1; i++)
			ver[i] = i;
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			if (ver[p.y] == ver[p.x])
				continue;
			boolean sel = false;
			int min = Math.min(ver[p.y], ver[p.x]);
			int max = Math.max(ver[p.y], ver[p.x]);
			for (int i = 1; i < num + 1; i++) {
				if (ver[i] == max) {
					ver[i] = min;
					sel = true;
				}
			}
			if (sel) {
				cnt++;
				res += p.w;
			}
		}
		if (cnt != num - 1)
			res = -1;

	}

	public static void bridge(int y, int x, int s) {
		for (int d = 0; d < 4; d++) {
			int ny = y;
			int nx = x;
			int l = 0;
			while (true) {
				ny = ny + dy[d];
				nx = nx + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == s)
					break;
				l++;
				if (map[ny][nx] != 0) {
					if (l > 2) {
						link[s][map[ny][nx]] = Math.min(link[s][map[ny][nx]], l - 1);
					}
					break;
				}
			}
		}
	}

	public static void sumNum(int y, int x, int n) {
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx])
				continue;
			if (map[ny][nx] == 1) {
				visited[ny][nx] = true;
				map[ny][nx] = n;
				sumNum(ny, nx, n);
			}
		}
	}

	static class Point implements Comparable<Point> {
		int y, x, w;

		public Point(int y, int x, int w) {
			this.y = y;
			this.x = x;
			this.w = w;
		}

		@Override
		public int compareTo(Point o) {
			if (this.w > o.w)
				return 1;
			else
				return -1;
		}

	}

}
