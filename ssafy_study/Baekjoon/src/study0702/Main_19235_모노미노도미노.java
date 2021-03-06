package study0702;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19235_모노미노도미노 {

	static int[][] green, blue;
	static int N, num, score, cnt;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		green = new int[6][4];
		blue = new int[6][4];
		num = 1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			down(t, x, 0, green, 0, num);
			down(t, y, 1, blue, 0, num);
			while (full(green))
				push(green);
			while (full(blue))
				push(blue);
			while (light(green))
				push(green);
			while (light(blue))
				push(blue);
			num++;
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j] != 0)
					cnt++;
				if (blue[i][j] != 0)
					cnt++;
			}
		}
		System.out.println(score);
		System.out.println(cnt);
	}
	
	static void push(int[][] arr) {
		boolean[][] visit = new boolean[6][4];
		for (int i = 5; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				if (arr[i][j] == 0 || visit[i][j])
					continue;
				visit[i][j] = true;
				boolean flag = false;
				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					if (ny < 0 || nx < 0 || ny >= 6 || nx >= 4 || visit[ny][nx] || arr[ny][nx] == 0)
						continue;
					if (arr[i][j] == arr[ny][nx]) {
						visit[ny][nx] = true;
						if (i == 5)
							continue;
						int num = arr[i][j];
						arr[i][j] = 0;
						arr[ny][nx] = 0;
						if (d == 1 || d == 3) {
							down(2, j, 0, arr, i + 1, num);
						} else if (d == 2 || d == 0) {
							down(3, j, 0, arr, i + 1, num);
						}
						flag = true;
						break;
					}
				}
				if (!flag && i != 5) {
					int num = arr[i][j];
					arr[i][j] = 0;
					down(1, j, 0, arr, i + 1, num);
				}
			}
		}
	}

	static boolean light(int[][] arr) {
		int cnt = 0;
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j < 4; j++) {
				if (arr[i][j] != 0) {
					cnt++;
					break;
				}
			}
		}
		if (cnt == 1) {
			for (int j = 0; j < 4; j++) {
				arr[5][j] = 0;
			}
			return true;
		} else if (cnt == 2) {
			for (int j = 0; j < 4; j++) {
				arr[5][j] = 0;
				arr[4][j] = 0;
			}
			return true;
		} else
			return false;
	}

	static boolean full(int[][] arr) {
		boolean in = false;
		for (int i = 0; i < 6; i++) {
			boolean check = true;
			for (int j = 0; j < 4; j++) {
				if (arr[i][j] == 0) {
					check = false;
					break;
				}
			}
			if (check) {
				in = true;
				for (int j = 0; j < 4; j++)
					arr[i][j] = 0;
				score++;
			}
		}
		if (in)
			return true;
		else
			return false;
	}

	static void down(int t, int n, int color, int[][] arr, int s, int num) {
		if (t == 1) {
			for (int i = s; i < 6; i++) {
				if (arr[i][n] == 0) {
					if (i == 5) {
						arr[i][n] = num;
					}
					continue;
				} else {
					arr[i - 1][n] = num;
					break;
				}
			}
		} else if ((t == 2 && color == 0) || (t == 3 && color == 1)) {
			for (int i = s; i < 6; i++) {
				if (arr[i][n] == 0 && arr[i][n + 1] == 0) {
					if (i == 5) {
						arr[i][n] = num;
						arr[i][n + 1] = num;
					}
					continue;
				} else {
					arr[i - 1][n] = num;
					arr[i - 1][n + 1] = num;
					break;
				}
			}
		} else if ((t == 3 && color == 0) || (t == 2 && color == 1)) {
			for (int i = s; i < 6; i++) {
				if (arr[i][n] == 0) {
					if (i == 5) {
						arr[i][n] = num;
						arr[i - 1][n] = num;
					}
					continue;
				} else {
					arr[i - 1][n] = num;
					arr[i - 2][n] = num;
					break;
				}
			}
		}
	}

}
