package study0404;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main_15683_감시 {

	static int N, M, ans;
	static int[] sel;
	static int[][] fmap, map;
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 }; // 우 하 좌 상
	static List<Point> cam = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		ans = Integer.MAX_VALUE;
		fmap = new int[N][M];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				fmap[i][j] = sc.nextInt();
				if (fmap[i][j] != 0 && fmap[i][j] != 6)
					cam.add(new Point(i, j, fmap[i][j]));
			}
		}
		sel = new int[cam.size()];
		combi(0);
		System.out.println(ans);
	}

	static void combi(int cnt) {
		if (cnt == cam.size()) {
			for (int i = 0; i < N; i++)
				System.arraycopy(fmap[i], 0, map[i], 0, M);
			cctv();
			ans = Math.min(ans, noCnt());
			return;
		}
		if (cam.get(cnt).n == 2) {
			for (int i = 0; i < 2; i++) {
				sel[cnt] = i;
				combi(cnt + 1);
			}
		} else if (cam.get(cnt).n == 5) {
			sel[cnt] = 0;
			combi(cnt + 1);
		} else {
			for (int i = 0; i < 4; i++) {
				sel[cnt] = i;
				combi(cnt + 1);
			}
		}
	}

	static int noCnt(){
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0)
					cnt++;
			}
		}
		return cnt;
	}
	
	static void cctv() {
		for (int i = 0; i < cam.size(); i++) {
			int y = cam.get(i).y;
			int x = cam.get(i).x;
			int n = cam.get(i).n;
			if (n == 1) {
				go(y, x, sel[i]);
			} else if (n == 2) {
				go(y, x, sel[i]);
				go(y, x, sel[i] + 2);
			} else if (n == 3) {
				go(y, x, sel[i]);
				go(y, x, sel[i] - 1 == -1 ? 3 : sel[i] - 1);
			} else if (n == 4) {
				for (int j = 0; j < 4; j++) {
					if (sel[i] == j)
						continue;
					go(y, x, j);
				}
			} else {
				for (int j = 0; j < 4; j++)
					go(y, x, j);
			}
		}
	}

	static void go(int y, int x, int d) {
		int ny = y + dy[d];
		int nx = x + dx[d];
		if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 6)
			return;
		if (map[ny][nx] != 0)
			go(ny, nx, d);
		else {
			map[ny][nx] = -1;
			go(ny, nx, d);
		}
	}

	static class Point {
		int y, x, n;

		public Point(int y, int x, int n) {
			this.y = y;
			this.x = x;
			this.n = n;
		}
	}
}
