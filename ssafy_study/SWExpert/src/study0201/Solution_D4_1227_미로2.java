package study0201;

import java.util.Scanner;

public class Solution_D4_1227_미로2 {

	static int T, sy, sx, fy, fx, res;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 1; t <= 10; t++) {
			T = sc.nextInt();
			map = new int[100][100];
			visited = new boolean[100][100];
			res = 0;

			for (int i = 0; i < 100; i++) {
				String[] s = sc.next().split("");
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(s[j]);
					if (map[i][j] == 2) {
						sy = i;
						sx = j;
					} else if (map[i][j] == 3) {
						fy = i;
						fx = j;
					}
				}
			}
			dfs(sy, sx);
			System.out.println("#" + T + " " + res);
		}

	}

	public static void dfs(int y, int x) {
		if (y == fy && x == fx) {
			res = 1;
			return;
		}
		visited[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= 100 || nx >= 100)
				continue;
			if (map[ny][nx] == 1 || map[ny][nx] == 2)
				continue;
			if (visited[ny][nx])
				continue;
			dfs(ny, nx);
		}

	}

}
