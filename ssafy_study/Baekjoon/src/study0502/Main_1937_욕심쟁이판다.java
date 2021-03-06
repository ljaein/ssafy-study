package study0502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1937_욕심쟁이판다 {

	static int N, ans;
	static int[][] map, day;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		day = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (day[i][j] == 0) {
					dfs(i, j);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans = Math.max(day[i][j], ans);
			}
		}
		System.out.println(ans);
	}

	static int dfs(int y, int x) {
		day[y][x] = 1;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] <= map[y][x])
				continue;
			if (day[ny][nx] != 0) {
				day[y][x] = Math.max(day[y][x], 1 + day[ny][nx]);
			} else {
				day[y][x] = Math.max(day[y][x], dfs(ny, nx) + 1);
			}
		}
		return day[y][x];
	}
}
