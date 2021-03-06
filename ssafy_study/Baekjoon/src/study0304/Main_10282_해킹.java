package study0304;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_10282_해킹 {

	static int n, d, c, cnt, ans;
	static List<Com>[] list;
	static int[] time;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			ans = cnt = 0;
			n = sc.nextInt();
			d = sc.nextInt();
			c = sc.nextInt() - 1;// 해킹
			list = new LinkedList[n];
			for (int i = 0; i < n; i++)
				list[i] = new LinkedList<Com>();
			for (int i = 0; i < d; i++) {
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				list[b].add(new Com(a, sc.nextInt()));
			}
			time = new int[n];
			Arrays.fill(time, Integer.MAX_VALUE);
			time[c] = 0;
			PriorityQueue<Com> pq = new PriorityQueue<>();
			pq.add(new Com(c, 0));
			while (!pq.isEmpty()) {
				Com cur = pq.poll();
				for (Com com : list[cur.c]) {
					if (time[com.c] > time[cur.c] + com.dep) {
						time[com.c] = time[cur.c] + com.dep;
						pq.add(new Com(com.c, time[com.c]));
					}
				}
			}
			for (int i = 0; i < n; i++) {
				if (time[i] != Integer.MAX_VALUE) {
					cnt++;
					ans = Math.max(ans, time[i]);
				}
			}
			System.out.println(cnt + " " + ans);
		}

	}

	static class Com implements Comparable<Com> {
		int c, dep;

		public Com(int c, int dep) {
			super();
			this.c = c;
			this.dep = dep;
		}

		@Override
		public int compareTo(Com arg0) {
			return this.dep - arg0.dep;
		}

	}
}
