package study0201;

import java.util.Scanner;

public class Solution_D4_1868_파핑파핑지뢰찾기 {

	static int T, N, res;
	static char[][] input;
	static int[][] map;
	static int[][] pop;
	static boolean[][] visited;
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			input = new char[N][N];
			map = new int[N][N];
			pop = new int[N][N];
			visited = new boolean[N][N];
			res = 0;
			for (int i = 0; i < N; i++) {
				String s = sc.next();
				input[i] = s.toCharArray();
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (input[i][j] == '*') {
						map[i][j] = -1;
						pop[i][j] = -1;
					} else {
						int cnt = 0;
						int y = i;
						int x = j;
						for (int d = 0; d < 8; d++) {
							int ny = y + dy[d];
							int nx = x + dx[d];
							if (ny < 0 || nx < 0 || ny >= N || nx >= N)
								continue;
							if (input[ny][nx] == '*') {
								cnt++;
							}
						}
						map[i][j] = cnt;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0 && pop[i][j] == 0) {
						int y = i;
						int x = j;
						dfs(y, x);
						res++;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (pop[i][j] == 1) {
						int y = i;
						int x = j;
						for (int d = 0; d < 8; d++) {
							int ny = y + dy[d];
							int nx = x + dx[d];
							if (ny < 0 || nx < 0 || ny >= N || nx >= N)
								continue;
							if (pop[ny][nx] != 1 && pop[ny][nx] != -1) {
								pop[ny][nx] = 2; // 0 �����̸� 2�� ����
							}

						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (pop[i][j] == 0) // ���� Ŭ���ؾ��ϴ� ĭ
						res++;
				}
			}

			System.out.println("#" + t + " " + res);
		}
	}

	private static void dfs(int y, int x) {
		pop[y][x] = 1;
		visited[y][x] = true;
		for (int d = 0; d < 8; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N)
				continue;
			if (visited[ny][nx])
				continue;
			if (map[ny][nx] == 0) {
				dfs(ny, nx);
			}
		}

	}

}
