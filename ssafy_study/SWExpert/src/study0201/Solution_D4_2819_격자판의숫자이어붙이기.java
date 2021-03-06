package study0201;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Solution_D4_2819_격자판의숫자이어붙이기 {

	static int T;
	static int[][] num;
	static int[] sel;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static TreeSet<String> ts;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			num = new int[4][4];
			sel = new int[7];
			ts = new TreeSet<>();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					num[i][j] = sc.nextInt();
				}
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					dfs(i, j, 0);
				}
			}
			System.out.println("#" + t + " " + ts.size());
		}

	}

	public static void dfs(int y, int x, int cnt) {
		if (cnt == 7) {
			ts.add(Arrays.toString(sel));
			return;
		}
		sel[cnt] = num[y][x];
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4)
				continue;
			dfs(ny, nx, cnt + 1);
		}
	}

}
