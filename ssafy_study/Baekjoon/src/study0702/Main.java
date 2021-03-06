package study0702;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Shark {
		int y;
		int x;
		int d;

		public Shark(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}

	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Shark shark[] = new Shark[17];
		int map[][] = new int[4][4];
//		shark[0] = new Shark(1, 1, -1);
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				shark[a] = new Shark(i, j, b - 1);
				map[i][j] = a;
			}
		}
		ans = Integer.MIN_VALUE;

		// 최대값을 찾는다
		DFS(0, 0, 0, shark, map);

		System.out.println(ans);

	}

	static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dx[] = { 0, -1, -1, -1, 0, 1, 1, 1 };

	private static void DFS(int y, int x, int cnt, Shark[] shark, int map[][]) {
		cnt = cnt + map[y][x];
		// y,x를 먹는다.
		ans = Math.max(ans, cnt);
		int d = shark[map[y][x]].d;
		// -1 이면 죽은 상어
		shark[map[y][x]].d = -1;
		map[y][x] = 0;
		// 상어는 1번부터 16번까지 이동한다.
		for (int i = 1; i <= 16; i++) {
			// 1번 상어부터 16번 상어까지 본다.
			if (shark[i].d == -1)
				continue;
			// 죽은 상어가 아니면 이동한다.

			for (int n = 0; n < 8; n++) {
				int sny = shark[i].y + dy[shark[i].d];
				int snx = shark[i].x + dx[shark[i].d];
				if (sny < 0 || snx < 0 || sny >= 4 || snx >= 4 || (sny == y && snx == x)) {
					shark[i].d = (shark[i].d + 1) % 8;
					continue;
				} else {
					// 빈곳인 경우
					if (map[sny][snx] == 0) {
						map[sny][snx] = map[shark[i].y][shark[i].x];
						map[shark[i].y][shark[i].x] = 0;
						shark[i].y = sny;
						shark[i].x = snx;
					}
					// 빈곳이 아닌 경우 swap
					else {
						// shark 위치를 바꾼다.
						int j = map[sny][snx];

						// 바꾼다

						int tmpy = shark[i].y;
						shark[i].y = shark[j].y;
						shark[j].y = tmpy;

						int tmpx = shark[i].x;
						shark[i].x = shark[j].x;
						shark[j].x = tmpx;

						map[shark[i].y][shark[i].x] = i;
						map[shark[j].y][shark[j].x] = j;
					}

					break;
				}
			}

		}
		// 상어가 이동한다.

		for (int i = 1; i <= 3; i++) {
			int ny = y + dy[d] * i;
			int nx = x + dx[d] * i;

			if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4)
				break;
			
			if (map[ny][nx] == 0)
				continue;

			int copyMap[][] = new int[4][4];
			Shark copyShark[] = new Shark[17];

			for (int j = 0; j < 4; j++)
				copyMap[j] = map[j].clone();
			for (int j = 1; j <= 16; j++)
				copyShark[j] = new Shark(shark[j].y, shark[j].x, shark[j].d);
			DFS(ny, nx, cnt, copyShark, copyMap);
		}

	}
}