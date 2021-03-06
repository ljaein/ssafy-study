package study0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1400_화물차 {

	static int M, N, R;
	static int sy, sx, ans;
	static char[][] map;
	static Cross[] cro;
	static int[][] visit;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while (true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			R = 0;
			ans = Integer.MAX_VALUE;
			if (M == 0 && N == 0)
				break;
			map = new char[M][N];
			for (int i = 0; i < M; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 'A') {
						sy = i;
						sx = j;
					} else {
						if (map[i][j] != '#' && map[i][j] != '.' && map[i][j] != 'B') {
							R++;
						}
					}
				}
			}
			if (R != 0) {
				cro = new Cross[R];
				for (int i = 0; i < R; i++) {
					st = new StringTokenizer(br.readLine());
					int n = Integer.parseInt(st.nextToken());
					char d = st.nextToken().charAt(0);
					int r = Integer.parseInt(st.nextToken());
					int c = Integer.parseInt(st.nextToken());
					cro[i] = new Cross(n, d == '-' ? 0 : 1, r, c);
				}
			}

			bfs();
			System.out.println(ans == Integer.MAX_VALUE ? "impossible" : ans);
			// print();
			br.readLine();
		}
	}

	private static void print() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] == Integer.MAX_VALUE)
					System.out.print("- ");
				else
					System.out.print(visit[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		visit = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		visit[sy][sx] = 0;
		q.add(new Point(sy, sx, 0));
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if (ny < 0 || nx < 0 | ny >= M || nx >= N || visit[ny][nx] < cur.cnt + 1 || map[ny][nx] == '.')
					continue;
				if (map[ny][nx] == 'B') {
					visit[ny][nx] = cur.cnt + 1;
					ans = Math.min(ans, cur.cnt + 1);
					continue;
				} else if (map[ny][nx] == '#') {
					visit[ny][nx] = cur.cnt + 1;
					q.add(new Point(ny, nx, cur.cnt + 1));
				} else {
					Cross c = cro[map[ny][nx] - '0'];
					int time = cur.cnt;
					int dnum = c.d;
					while (time >= 0) {
						if (dnum == 0) {
							time -= c.r;
							dnum = 1;
						} else {
							time -= c.c;
							dnum = 0;
						}

					}
					if (d == 1 || d == 3) {
						if (dnum == 1) { // 동서로 끝
							visit[ny][nx] = cur.cnt + 1;
							q.add(new Point(ny, nx, cur.cnt + 1));
						} else {
							if (visit[ny][nx] > cur.cnt - time + 1) {
								visit[ny][nx] = cur.cnt - time + 1;
								q.add(new Point(ny, nx, cur.cnt - time + 1));
							}
						}
					} else {
						if (dnum == 0) {
							visit[ny][nx] = cur.cnt + 1;
							q.add(new Point(ny, nx, cur.cnt + 1));
						} else {
							if (visit[ny][nx] > cur.cnt - time + 1) {
								visit[ny][nx] = cur.cnt - time + 1;
								q.add(new Point(ny, nx, cur.cnt - time + 1));
							}
						}
					}
				}
			}
		}
	}

	static class Point {
		int y, x, cnt;

		public Point(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

	}

	static class Cross {
		int n, d, r, c;

		public Cross(int n, int d, int r, int c) {
			super();
			this.n = n;
			this.d = d;
			this.r = r;
			this.c = c;
		}
	}

}