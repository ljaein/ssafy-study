package study0203;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_17142_연구소3 {

	static int N, M, res, min;
	static int[][] fix_map, map;
	static boolean[][] visited;
	static int[] sel;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static ArrayList<Point> virus = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		fix_map = new int[N][N];
		map = new int[N][N];
		sel = new int[M];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				fix_map[i][j] = sc.nextInt();
				if (fix_map[i][j] == 2) {
					virus.add(new Point(i, j, 0));
				}
			}
		}
		combi(0, 0);
		bfs();
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	public static void combi(int start, int cnt) {
		if (cnt == M) {
			for (int i = 0; i < N; i++)
				System.arraycopy(fix_map[i], 0, map[i], 0, N);
			visited = new boolean[N][N];
			bfs();
			if (check())
				min = Math.min(min, res);
			return;
		}
		for (int i = start; i < virus.size(); i++) {
			sel[cnt] = i;
			combi(i + 1, cnt + 1);
		}
	}

	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			Point act = virus.get(sel[i]);
			map[act.y][act.x] = 1;
			q.add(act);
		}
		if (check()) {
			res = 0;
			return;
		}
		Point cur = null;
		while (!q.isEmpty()) {
			int qsize = q.size();
			for (int s = 0; s < qsize; s++) {
				cur = q.poll();
				for (int d = 0; d < 4; d++) {
					int ny = cur.y + dy[d];
					int nx = cur.x + dx[d];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx])
						continue;
					if (map[ny][nx] != 1) {
						visited[ny][nx] = true;
						map[ny][nx] = 1;
						q.add(new Point(ny, nx, cur.t + 1));
					}
				}
			}
			if (check()) {
				res = cur.t + 1;
				break;
			}
		}
	}

	public static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
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
