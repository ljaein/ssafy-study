package study0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_7699_수지의수지맞는여행 {

	static int T, R, C, max;
	static char[][] map;
	static boolean[] alpha;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			alpha = new boolean[26];
			max = 0;
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
			}
			alpha[map[0][0] - 'A'] = true;
			go(0, 0, 1);
			System.out.println("#" + t + " " + max);

		}
	}

	public static void go(int y, int x, int cnt) {
		if (cnt == 27)
			return;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny < 0 || nx < 0 || ny >= R || nx >= C || alpha[map[ny][nx] - 'A']) {
				max = Math.max(max, cnt);
				continue;
			}
			alpha[map[ny][nx] - 'A'] = true;
			go(ny, nx, cnt + 1);
			alpha[map[ny][nx] - 'A'] = false;
		}
	}

}
