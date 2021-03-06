package study0203;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main_17135_캐슬디펜스 {

	static int N, M, D, res, max;
	static int[][] fix_map, map;
	static int[] gung;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		fix_map = new int[N][M];
		map = new int[N][M];
		gung = new int[3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				fix_map[i][j] = sc.nextInt();
			}
		}
		combi(0, 0);
		while (!check()) {
			attack(gung);
			move();
		}
		System.out.println(max);
	}

	public static void combi(int start, int cnt) {
		if (cnt == 3) {
			for (int i = 0; i < N; i++)
				System.arraycopy(fix_map[i], 0, map[i], 0, M);
			while (!check()) {
				attack(gung);
				move();
			}
			max = Math.max(max, res);
			res = 0;
			return;
		}
		for (int i = start; i < M; i++) {
			gung[cnt] = i;
			combi(i + 1, cnt + 1);
		}
	}

	public static void attack(int[] gung) {
		LinkedList<Point> kill = new LinkedList<>();
		for (int g = 0; g < 3; g++) {
			LinkedList<Point> list = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						int dis = Math.abs(N - i) + Math.abs(gung[g] - j);
						if (dis <= D) {
							list.add(new Point(i, j, dis));
						}
					}
				}
			}
			// 가장 가까운 적 고르기 (왼쪽부터 들어가있음) -> 겹칠 수도 있음
			if (list.size() != 0) {
				list.sort(new C());
				kill.add(new Point(list.get(0).y, list.get(0).x));
			}
		}
		while (!kill.isEmpty()) {
			Point p = kill.poll();
			if (map[p.y][p.x] != 0) {
				map[p.y][p.x] = 0;
				res++;
			}
		}
	}

	public static void move() {
		LinkedList<Point> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					map[i][j] = 0;
					if (i + 1 < N) {
						list.add(new Point(i + 1, j));
					}
				}
			}
		}
		while (!list.isEmpty()) {
			Point p = list.poll();
			map[p.y][p.x] = 1;
		}
	}

	public static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static class C implements Comparator<Point> {

		@Override
		public int compare(Point arg0, Point arg1) {
			if (arg0.d > arg1.d)
				return 1;
			else if (arg0.d == arg1.d) {
				if (arg0.x > arg1.x)
					return 1;
				else
					return -1;
			} else
				return -1;
		}

	}

	static class Point {
		int y, x, d;

		public Point(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
