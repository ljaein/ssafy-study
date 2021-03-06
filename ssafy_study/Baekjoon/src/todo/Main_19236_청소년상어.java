package todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_19236_청소년상어 {

	static int ans;
	static Fish[] ffish;
	static int[][] fmap;
	static int[][] fshark;
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		ffish = new Fish[17];
		fmap = new int[4][4];
		fshark = new int[1][3];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				fmap[i][j] = a;
				ffish[a] = new Fish(a, i, j, b);
			}
		}
		int num = fmap[0][0];
		fshark[0][0] = fshark[0][1] = 0;
		fshark[0][2] = ffish[fmap[0][0]].d;
		ffish[fmap[0][0]] = null;
		fmap[0][0] = -1; // 상어는 -1, 빈칸은 0
		move(ffish, fmap, fshark, num);
		System.out.println(ans);

	}

	static void move(Fish[] ffish, int[][] fmap, int[][] fshark, int sum) {
		ans = Math.max(ans, sum);
		Fish[] fish = new Fish[17];
		int[][] map = new int[4][4];
		int[][] shark = new int[1][3];
		System.arraycopy(ffish, 0, fish, 0, 17);
		for (int i = 0; i < 4; i++)
			System.arraycopy(fmap[i], 0, map[i], 0, 4);
		System.arraycopy(fshark[0], 0, shark[0], 0, 3);

		for (int f = 1; f < 17; f++) {
			if (fish[f] == null)
				continue;
			Fish cur = fish[f];
			int nd = cur.d;
			for (int d = 0; d < 8; d++) {
				int ny = cur.y + dy[nd];
				int nx = cur.x + dx[nd];
				if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4 || map[ny][nx] == -1) {
					nd = nd == 8 ? 1 : nd + 1;
					continue;
				}
				if (map[ny][nx] == 0) { // 빈칸이면
					map[cur.y][cur.x] = 0;
					map[ny][nx] = cur.n;
					cur.y = ny;
					cur.x = nx;
					cur.d = nd;
					break;
				} else { // 다른물고기 있으면
					int temp = map[ny][nx];
					map[ny][nx] = cur.n;
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
			Fish[] fish2 = new Fish[17];
			int[][] map2 = new int[4][4];
			int[][] shark2 = new int[1][3];
			for (int i = 1; i < 17; i++){
				if(fish[i]!=null)
				fish2[i] = new Fish(fish[i].n,fish[i].y, fish[i].x, fish[i].d);
			}
			for (int i = 0; i < 4; i++)
				System.arraycopy(map[i], 0, map2[i], 0, 4);
			System.arraycopy(shark[0], 0, shark2[0], 0, 3);

			int ny = shark2[0][0] + dy[shark2[0][2]] * d;
			int nx = shark2[0][1] + dx[shark2[0][2]] * d;
			if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4)
				break;
			if (map2[ny][nx] == 0)
				continue;
			int num = map2[ny][nx];
			map2[shark2[0][0]][shark2[0][1]] = 0;
			shark2[0][0] = ny;
			shark2[0][1] = nx;
			shark2[0][2] = fish2[map2[ny][nx]].d;
			fish2[map2[ny][nx]] = null;
			map2[ny][nx] = -1;
			move(fish2, map2, shark2, sum + num);
		}

	}

	static class Fish {
		int n, y, x, d;

		public Fish(int n, int y, int x, int d) {
			super();
			this.n = n;
			this.y = y;
			this.x = x;
			this.d = d;
		}

	}
}
