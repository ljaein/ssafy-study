package study0501;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_6118_숨바꼭질 {

	static int N, M, min, dis, cnt;
	static List<Integer>[] list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		list = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new LinkedList<>();
		}
		for (int i = 0; i < M; i++) {
			int A_i = sc.nextInt() - 1;
			int B_i = sc.nextInt() - 1;
			list[A_i].add(B_i);
			list[B_i].add(A_i);
		}
		bfs();
		System.out.println((min + 1) + " " + (dis - 1) + " " + cnt);
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean visit[] = new boolean[N];
		visit[0] = true;
		q.add(0);
		dis = 0;
		while (!q.isEmpty()) {
			int qsize = q.size();
			min = Integer.MAX_VALUE;
			cnt = qsize;
			for (int s = 0; s < qsize; s++) {
				int cur = q.poll();
				min = Math.min(min, cur);
				for (int i = 0; i < list[cur].size(); i++) {
					if (!visit[list[cur].get(i)]) {
						visit[list[cur].get(i)] = true;
						q.add(list[cur].get(i));
					}
				}
			}
			dis++;
		}
	}

}
