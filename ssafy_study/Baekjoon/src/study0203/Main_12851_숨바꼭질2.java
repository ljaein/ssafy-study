package study0203;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_12851_숨바꼭질2 {

	static int N, K, cnt, res;
	static boolean[] visited;
	static int[] check;
	static Queue<Integer> q;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		visited = new boolean[100001];
		check = new int[3];
		q = new LinkedList<Integer>();
		visited[N] = true;
		q.add(N);
		boolean answer = false;
		while (!q.isEmpty()) {
			int qsize = q.size();
			if(answer)
				break;
			for (int i = 0; i < qsize; i++) {
				int n = q.poll();
				visited[n]=true;
				if (n == K) {
					res++;
					answer = true;
				}
				check[0] = n - 1;
				check[1] = n + 1;
				check[2] = n * 2;
				for (int d = 0; d < 3; d++) {
					if (check[d] < 0 || check[d] >= 100001 || visited[check[d]])
						continue;
					q.add(check[d]);
				}
			}
			cnt++;
		}
		System.out.println(cnt-1);
		System.out.println(res);

	}

}
