package study0203;

import java.util.Scanner;

public class Main_3109_빵집2 {

	static int R, C, max;
	static char[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 0, 1 };
	static int[] dx = { 1, 1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		max = 0;
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = sc.next().toCharArray();
		}
		visited[0][0] = true;
		dfs(0, 0, 0,0);

		System.out.println(max);

	}

	public static void dfs(int y, int x, int r, int cnt) {
		System.out.println("y: " + y + " x: " + x);
		if (x == C - 1) {
			cnt++;
			if (y == R - 1) {
				System.out.println("innnnnnnnnn" + cnt);
				max = Math.max(max, cnt);
				return;
			}
			System.out.println("---------");
			visited[r + 1][0] = true;
			dfs(r + 1, 0, r + 1, cnt);
			//visited[r + 1][0] = false;
		}
		boolean chk = false;

		for (int i = 0; i < 3; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= R || nx >= C || visited[ny][nx])
				continue;
			if (map[ny][nx] == '.') {
				chk = true;
				visited[ny][nx] = true;
				dfs(ny, nx, r,cnt);
				//visited[ny][nx] = false;
			}
		}
		if (y == R - 1 && !chk) {
			System.out.println("iiiiiin" + cnt);
			max = Math.max(max, cnt);
			return;
		}
	}
}
