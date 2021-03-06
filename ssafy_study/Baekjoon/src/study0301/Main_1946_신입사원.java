package study0301;

import java.util.Arrays;
import java.util.Scanner;

public class Main_1946_신입사원 {

	static int T, N;
	static Point[] grade;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			grade = new Point[N];
			for (int i = 0; i < N; i++) {
				grade[i] = new Point(sc.nextInt(), sc.nextInt());
			}
			Arrays.sort(grade);
			int min = grade[0].b;
			int cnt = 1;
			for (int i = 1; i < N; i++) {
				if (grade[i].b == 1) {
					cnt++;
					break;
				}
				if (min > grade[i].b) {
					min = grade[i].b;
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}

	static class Point implements Comparable<Point> {
		int a, b;

		public Point(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Point o) {
			if (this.a > o.a)
				return 1;
			else
				return -1;
		}

		@Override
		public String toString() {
			return "Point [a=" + a + ", b=" + b + "]";
		}

	}

}
