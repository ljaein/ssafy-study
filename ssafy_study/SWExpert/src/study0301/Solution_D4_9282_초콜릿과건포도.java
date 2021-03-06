package study0301;

import java.util.Scanner;

public class Solution_D4_9282_초콜릿과건포도 {

	static int T, N, M;
	static int[][] map;
	static int[][][][] memo;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][M];
			memo = new int[N+1][M+1][N+1][M+1];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			System.out.println("#" + t + " " + cut(0, 0, N, M));
		}
	}

	public static int cut(int y, int x, int yl, int xl) {
		if (yl == 1 && xl == 1)
			return 0;
		
		if (memo[y][x][yl][xl] != 0)
			return memo[y][x][yl][xl];
		
		int sum = 0;
		for (int i = y; i < y + yl; i++) {
			for (int j = x; j < x + xl; j++) {
				sum += map[i][j];
			}
		}
	
		int res = Integer.MAX_VALUE;
		for (int i = 1; i < yl; i++)
			res = Math.min(res, sum + cut(y, x, i, xl) + cut(y + i, x, yl - i, xl));
		for (int j = 1; j < xl; j++)
			res = Math.min(res, sum + cut(y, x, yl, j) + cut(y, x + j, yl, xl - j));

		memo[y][x][yl][xl] = res;
		return res;
	}

}
