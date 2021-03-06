package study0403;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_3190_뱀 {

	static int N, K, L;
	static int time, d, yhead, xhead;
	static int[][] map;
	static int[] rtime;
	static char[] rdir;
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		K = sc.nextInt();
		for (int i = 0; i < K; i++) {
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			map[y][x] = -1;
		}
		L = sc.nextInt();
		rtime = new int[L];
		rdir = new char[L];
		for (int i = 0; i < L; i++) {
			rtime[i] = sc.nextInt();
			rdir[i] = sc.next().charAt(0);
		}
		map[0][0] = 1;
		q.add(new Point(0, 0));
		go();
		System.out.println(time);
	}

	static void go() {
		int rcnt = 0;
		while (true) {
			if (rtime[rcnt] == time) {
				if (rdir[rcnt] == 'D')
					d = (d == 3 ? 0 : d + 1);
				else
					d = (d == 0 ? 3 : d - 1);
				if (rcnt < L - 1)
					rcnt++;
			}
			int ny = yhead + dy[d];
			int nx = xhead + dx[d];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 1) {
				time++;
				break;
			}
			q.add(new Point(ny, nx));
			yhead = ny;
			xhead = nx;
			if (map[ny][nx] == 0) {
				Point cur = q.poll();
				map[cur.y][cur.x] = 0;
			}
			map[ny][nx] = 1;
			time++;
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
