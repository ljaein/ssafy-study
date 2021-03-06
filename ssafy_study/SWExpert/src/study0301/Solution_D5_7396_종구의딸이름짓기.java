package study0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D5_7396_종구의딸이름짓기 {

	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static StringBuilder sb;
	static int[] dy = { 0, 1 };
	static int[] dx = { 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			sb = new StringBuilder();
			bfs();
			System.out.println("#" + t + " " + sb.toString());
		}

	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0,map[0][0]));
		while (!q.isEmpty()) {
			LinkedList<Point> list = new LinkedList<>();
			char min = 'z' + 1;
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point p = q.poll();
				if (min > p.c)
					min = p.c;
				list.add(p);

			}
			for (int i = 0; i < size; i++) {
				if (list.get(i).c == min)
					q.add(list.get(i));
			}
			sb.append(min);
			size = q.size();
			for (int s = 0; s < size; s++) {
				Point cur = q.poll();
				for (int d = 0; d < 2; d++) {
					int y = cur.y + dy[d];
					int x = cur.x + dx[d];
					if (y < 0 || x < 0 || y >= N || x >= M || visited[y][x])
						continue;
					visited[y][x] = true;
					q.add(new Point(y, x,map[y][x]));
				}
			}
		}
	}

	static class Point {
		int y, x;
		char c;

		public Point(int y, int x, char c) {
			super();
			this.y = y;
			this.x = x;
			this.c=c;
		}

	}

}
