package study0203;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_16236_아기상어 {

	static int N;
	static int sy, sx;//출발점
	static int size, time, eat; //아기상어 크기, 걸리는 시간, 먹은 개수 
	static int ey, ex; //먹을 상어의 좌표
	static int[][] map;
	static boolean[][] visited;
	static Queue<Point> q;
	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		size = 2;
		eat = 0;
		time = 0;
		ey = Integer.MAX_VALUE;
		ex = Integer.MAX_VALUE;
		q = new LinkedList<>();
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					sy = i;
					sx = j;
				}
			}
		}

		visited[sy][sx] = true;
		q.add(new Point(sy, sx, 0));
		map[sy][sx] = 0;
		go(sy, sx);
		System.out.println(time);
	}

	public static void go(int y, int x) {
		boolean ok = false;
		while (!q.isEmpty()) {
			int qsize = q.size();
			Point cur = null;
			for (int i = 0; i < qsize; i++) {
				cur = q.poll();
				for (int d = 0; d < 4; d++) {
					int ny = cur.y + dy[d];
					int nx = cur.x + dx[d];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx])
						continue;
					visited[ny][nx] = true;
					if (map[ny][nx] != 0 && map[ny][nx] < size) { // 먹을 수 있는 물고기  O
						if (ey > ny) {
							ey = ny;
							ex = nx;
						} else if (ey == ny) {
							if (ex > nx)
								ex = nx;
						} // 상좌우하 순서 비교
						ok = true; // 먹으러 가자
					} else {
						if (map[ny][nx] <= size) { // 갈 수 있는 칸 O
							q.add(new Point(ny, nx, cur.t + 1));
						}
					}
				}
			}
			if (ok) { // 먹는 과정
				q.clear();
				visited = new boolean[N][N];
				visited[ey][ex] = true;
				map[ey][ex] = 0;
				q.add(new Point(ey, ex, cur.t + 1));
				time = cur.t + 1;
				eat++;
				if (size == eat) {
					size++;
					eat = 0;
				}
				ey = Integer.MAX_VALUE;
				ex = Integer.MAX_VALUE;
				ok = false;
			}

		}

	}

	static class Point {
		int y, x, t;

		public Point(int y, int x, int t) {
			this.y = y;
			this.x = x;
			this.t = t;
		}
	}

}
