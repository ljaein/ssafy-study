package study0702;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_19236_청소년상어 {

	static int ans;
	static Fish[] fish;
	static int[][] map;
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		fish = new Fish[17];
		map = new int[4][4];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[i][j] = a;
				fish[a] = new Fish(i, j, b);
			}
		}
		move(fish, map, 0, 0, 0);
		System.out.println(ans);
	}

	static void move(Fish[] fish, int[][] map, int y, int x, int sum) {
		sum += map[y][x];
		ans = Math.max(ans, sum);
		int shd = fish[map[y][x]].d;
		fish[map[y][x]] = null;
		map[y][x] = -1;

		for (int i = 1; i < 17; i++) {
			if (fish[i] == null)
				continue;
			Fish cur = fish[i];
			int nd = cur.d;
			for (int d = 0; d < 8; d++) {
				int ny = cur.y + dy[nd];
				int nx = cur.x + dx[nd];
				if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4 || map[ny][nx] == -1) {
					nd = nd == 8 ? 1 : nd + 1;
					continue;
				}
				if (map[ny][nx] == 0) { // 빈칸이면
					map[ny][nx] = map[cur.y][cur.x];
					map[cur.y][cur.x] = 0;
					cur.y = ny;
					cur.x = nx;
					cur.d = nd;
					break;
				} else { // 다른물고기 있으면
					int temp = map[ny][nx];
					map[ny][nx] = map[cur.y][cur.x];
					map[cur.y][cur.x] = temp;
					int ty = cur.y;
					int tx = cur.x;
					cur.y = ny;
					cur.x = nx;
					cur.d = nd;
					fish[temp].y = ty;
					fish[temp].x = tx;
					break;
				}
			}
		} // 물고기들 이동
		for (int d = 1; d <= 3; d++) {
			int ny = y + dy[shd] * d;
			int nx = x + dx[shd] * d;
			if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4)
				break;
			if (map[ny][nx] == 0)
				continue;
			Fish[] copyFish = new Fish[17];
			int[][] copyMap = new int[4][4];
			for (int i = 1; i < 17; i++) {
				if (fish[i] != null)
					copyFish[i] = new Fish(fish[i].y, fish[i].x, fish[i].d);
			}
			for (int i = 0; i < 4; i++)
				System.arraycopy(map[i], 0, copyMap[i], 0, 4);
			move(copyFish, copyMap, ny, nx, sum);
		}

	}

	static class Fish {
		int y, x, d;

		public Fish(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}

	}
}
