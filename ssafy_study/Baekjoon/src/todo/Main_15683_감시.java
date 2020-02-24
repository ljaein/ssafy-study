package todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_15683_감시 {

	static int N, M, min;
	static int[][] map;
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 }; // 우 하 좌 상
	static ArrayList<Point> cam = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		min = Integer.MAX_VALUE;
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] != 0 && map[i][j] != 6)
					cam.add(new Point(i, j, map[i][j]));
			}
		}
		cctv(0);
		System.out.println(min);
	}

	public static void cctv(int cidx) {
		if(cidx == cam.size()){
			for(int i=0;i<N;i++){
				for(int j=0;j<M;j++){
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			int cnt = 0;
			for(int i=0;i<N;i++){
				for(int j=0;j<M;j++){
					if(map[i][j]==0)
						cnt++;
				}
			}
			System.out.println("cnt"+cnt);
			min = Math.min(min, cnt);
			return;
		}
		for (int i = cidx; i < cam.size(); i++) {
			
			Point cur = cam.get(i);
			if (cur.n == 1) {
				
				right(cur.y,cur.x);
				cctv(cidx+1);
				down(cur.y,cur.x);
				cctv(cidx+1);
				left(cur.y,cur.x);
				cctv(cidx+1);
				up(cur.y,cur.x);
				cctv(cidx+1);
			} else if (cur.n == 2) {
				left(cur.y,cur.x);right(cur.y,cur.x);
				cctv(cidx+1);
				
				up(cur.y,cur.x);down(cur.y,cur.x);
				cctv(cidx+1);
			} else if (cur.n == 3) {
				right(cur.y,cur.x);up(cur.y,cur.x);
				cctv(cidx+1);
				
				right(cur.y,cur.x);down(cur.y,cur.x);
				cctv(cidx+1);
				
				left(cur.y,cur.x);up(cur.y,cur.x);
				cctv(cidx+1);
				
				left(cur.y,cur.x);down(cur.y,cur.x);
				cctv(cidx+1);
			} else if (cur.n == 4) {

			} else { // cur.n==5
				left(cur.y,cur.x);down(cur.y,cur.x);
				right(cur.y,cur.x);up(cur.y,cur.x);
				cctv(cidx+1);
				
			}
		}
	}

	public static void right(int y, int x) {
		for (int i = x + 1; i < M; i++) {
			if (map[y][i] == 6)
				return;
			if (map[y][i] == 0)
				map[y][i] = -1;
		}
	}

	public static void left(int y, int x) {
		for (int i = 0; i < x; i++) {
			if (map[y][i] == 6)
				return;
			if (map[y][i] == 0)
				map[y][i] = -1;
		}
	}

	public static void up(int y, int x) {
		for (int i = 0; i < y; i++) {
			if (map[i][x] == 6)
				return;
			if (map[i][x] == 0)
				map[i][x] = -1;
		}
	}

	public static void down(int y, int x) {
		for (int i = y + 1; i < N; i++) {
			if (map[i][x] == 6)
				return;
			if (map[i][x] == 0)
				map[i][x] = -1;
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
