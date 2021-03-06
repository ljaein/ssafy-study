package study0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_D5_1907_모래성쌓기 {

	static int T, H, W, res;
	static int[][] map, cnt;
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String[] s = br.readLine().split(" ");
			H = Integer.parseInt(s[0]);
			W = Integer.parseInt(s[1]);
			map = new int[H][W];
			cnt = new int[H][W];
			res = 0;
			for (int i = 0; i < H; i++) {
				char[] c = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					if (c[j] == '.')
						map[i][j] = 0;
					else
						map[i][j] = c[j] - '0';
				}
			}

			Queue<Point> q = new LinkedList<>();
			for (int i = 1; i < H - 1; i++) {
				for (int j = 1; j < W - 1; j++) {
					if (map[i][j] == 0)
						continue;
					for (int d = 0; d < 8; d++) {
						if (map[i + dy[d]][j + dx[d]] == 0)
							cnt[i][j]++;
					}
					if (map[i][j] <= cnt[i][j]) {
						q.add(new Point(i, j));
					}

				}
			}

			while (!q.isEmpty()) {
				int qsize = q.size();
				for (int qs = 0; qs < qsize; qs++) {
					Point cur = q.poll();
					map[cur.y][cur.x] = 0;
					for (int d = 0; d < 8; d++) {
						int y = cur.y + dy[d];
						int x = cur.x + dx[d];
						if (map[y][x] == 0)
							continue;
						if (map[y][x] > cnt[y][x]) {
							cnt[y][x]++;
							if (map[y][x] <= cnt[y][x])
								q.add(new Point(y, x));
						}
					}
				}
				res++;
			}
			System.out.println("#" + t + " " + res);
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
