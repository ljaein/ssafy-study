package todo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_3055_탈출 {

	static int R, C;
	static int fy, fx;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Point> wq, q;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		visited = new boolean[R][C];
		wq = new LinkedList<>();
		q = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			map[i] = sc.next().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					visited[i][j] = true;
					q.add(new Point(i, j));
				} else if (map[i][j] == 'D') {
					fy = i;
					fx = j;
				} else if (map[i][j] == '*') {
					visited[i][j] = true;
					wq.add(new Point(i, j));
				}
			}
		}

	}

	public static void go() {
		while (!wq.isEmpty() && !q.isEmpty()) {
			Point wcur = wq.poll();
			Point cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int wy = wcur.y + dy[d];
				int wx = wcur.x + dx[d];
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				
			}
		}
	}

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
