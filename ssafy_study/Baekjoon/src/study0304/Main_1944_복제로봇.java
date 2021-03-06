package study0304;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main_1944_복제로봇 {

	static int N, M, ans;
	static boolean flag;
	static char[][] map;
	static boolean[][] visit;
	static int[][] nodeNum;
	static int[] ver;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static Queue<Point> q;
	static PriorityQueue<Point> pq = new PriorityQueue<>();
	static LinkedList<Point> list = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][N];
		nodeNum = new int[N][N];
		ver = new int[M + 1];
		for (int i = 0; i <= M; i++)
			ver[i] = i;
		int num = 0;
		for (int i = 0; i < N; i++) {
			map[i] = sc.next().toCharArray();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'S' || map[i][j] == 'K') {
					list.add(new Point(i, j, 0));
					nodeNum[i][j] = num++;
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			flag = false;
			bfs(i);
			if (flag)
				break;
		}
		int chk = 0;
		while (!pq.isEmpty()) {
			if (chk == M)
				break;
			Point p = pq.poll();
			int v1 = ver[p.y];
			int v2 = ver[p.x];
			if (v1 != v2) {
				for (int i = 0; i <= M; i++) {
					if (ver[i] == v2) {
						ver[i] = v1;
					}
				}
				ans += p.cnt;
				chk++;
			}
		}
		System.out.println(flag ? -1 : ans);
	}

	static void bfs(int f) {
		q = new LinkedList<>();
		visit = new boolean[N][N];
		int y = list.get(f).y;
		int x = list.get(f).x;
		visit[y][x] = true;
		q.add(new Point(y, x, 0));
		int vcnt = M;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || map[ny][nx] == '1')
					continue;
				visit[ny][nx] = true;
				if (map[ny][nx] == 'K' || map[ny][nx] == 'S') {
					vcnt--;
					pq.add(new Point(f, nodeNum[ny][nx], cur.cnt + 1));
				}
				q.add(new Point(ny, nx, cur.cnt + 1));
			}
		}
		if (vcnt != 0)
			flag = true;

	}

	static class Point implements Comparable<Point> {
		int y, x, cnt;

		public Point(int y, int x, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point arg0) {
			return this.cnt - arg0.cnt;
		}

	}

}
