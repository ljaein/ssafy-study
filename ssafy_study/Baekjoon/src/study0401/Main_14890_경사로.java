package study0401;

import java.util.Scanner;

public class Main_14890_경사로 {

	static int N, L;
	static int[][] map;
	static boolean[][] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int cnt = 0;
		// 가로줄 검사
		for (int i = 0; i < N; i++) {
			boolean check = true;
			jf: for (int j = 0; j < N - 1; j++) {
				if (map[i][j] == map[i][j + 1]) {
					continue;
				} else if (map[i][j] + 1 == map[i][j + 1]) {// 1 커진 경우 경사로 놓기
					for (int k = j; k > j - L; k--) {
						if (!safe(i, k) || map[i][k] != map[i][j] || visit[i][k]) {
							check = false;
							break jf;
						}
						visit[i][k] = true;
					}
				} else if (map[i][j] - 1 == map[i][j + 1]) {// 1 작아진 경우 경사로 놓기
					for (int k = j + 1; k < j + 1 + L; k++) {
						if (!safe(i, k) || map[i][k] != map[i][j + 1] || visit[i][k]) {
							check = false;
							break jf;
						}
						visit[i][k] = true;
					}
					j += L - 1;
				} else {
					check = false;
					break jf;
				}
			}
			if (check) 
				cnt++;
			
		}
		// 세로줄 검사
		visit = new boolean[N][N];
		for (int j = 0; j < N; j++) {
			boolean check = true;
			jf: for (int i = 0; i < N - 1; i++) {
				if (map[i][j] == map[i + 1][j]) {
					continue;
				} else if (map[i][j] + 1 == map[i + 1][j]) {// 1 커진 경우
					for (int k = i; k > i - L; k--) {
						if (!safe(k, j) || map[k][j] != map[i][j] || visit[k][j]) {
							check = false;
							break jf;
						}
						visit[k][j] = true;
					}
				} else if (map[i][j] - 1 == map[i + 1][j]) {// 1 작아진 경우
					for (int k = i + 1; k < i + 1 + L; k++) {
						if (!safe(k, j) || map[k][j] != map[i + 1][j] || visit[k][j]) {
							check = false;
							break jf;
						}
						visit[k][j] = true;
					}
					i += L - 1;
				} else {
					check = false;
					break jf;
				}
			}
			if (check) 
				cnt++;
			
		}
		System.out.println(cnt);
	}

	static boolean safe(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= N)
			return false;
		return true;
	}

}
