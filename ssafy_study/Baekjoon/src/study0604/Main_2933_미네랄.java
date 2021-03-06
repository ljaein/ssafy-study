package study0604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2933_미네랄 {

	static int R, C, N;
	static char[][] map;
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		boolean isL = true;
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (isL) {
				for (int j = 0; j < C; j++) {
					if (map[R - n][j] == 'x') {
						map[R - n][j] = '.';
						break;
					}
				}
			} else {
				for (int j = C - 1; j >= 0; j--) {
					if (map[R - n][j] == 'x') {
						map[R - n][j] = '.';
						break;
					}
				}
			}
			fall();
			isL = isL ? false : true;
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		

	}

	static void fall() {
		char[][] cmap = new char[R][C];
		boolean[][] visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			System.arraycopy(map[i], 0, cmap[i], 0, C);
		}
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < C; i++) {
			if (cmap[R - 1][i] == 'x') {
				cmap[R - 1][i] = '0';
				visit[R - 1][i] = true;
				q.add(new Point(R - 1, i));
			}
		}
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if (ny < 0 || nx < 0 || ny >= R || nx >= C || visit[ny][nx])
					continue;
				if (cmap[ny][nx] == 'x') {
					visit[ny][nx] = true;
					cmap[ny][nx] = '0';
					q.add(new Point(ny, nx));
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int j = 0; j < C; j++) {
			for (int i = 0; i < R; i++) {
				if (cmap[i][j] == 'x') {
					int cnt = 0;
					int ni = i;
					while (true) {
						ni++;
						if (ni == R){
							min = Math.min(min, cnt);
							break;
						}
						if (cmap[ni][j] == '.')
							cnt++;
						else if (cmap[ni][j] == '0')
							min = Math.min(min, cnt);
						else
							cnt = 0;
					}
					break;
				}
			}
			
		}
		if (min != Integer.MAX_VALUE) {
			map = new char[R][C];
			for (int i = 0; i < R; i++) {
				Arrays.fill(map[i], '.');
			}
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (cmap[i][j] == '0')
						map[i][j] = 'x';
					else if (cmap[i][j] == 'x') {
						map[i + min][j] = 'x';
					}
				}
			}
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
