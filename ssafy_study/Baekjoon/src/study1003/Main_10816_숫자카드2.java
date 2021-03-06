package study1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10816_숫자카드2 {
	static int N, M;
	static int[] narr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		narr = new int[N];
		for (int i = 0; i < N; i++) {
			narr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(narr);
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			int ans = searchRight(num) - searchLeft(num) + 1;
			sb.append(ans).append(" ");
		}
		System.out.println(sb.toString());

	}

	static int searchRight(int num) {
		int fir = 0;
		int end = N;
		while (true) {
			if (fir >= end)
				break;
			int mid = (fir + end) / 2;
			if (narr[mid] == num) {
				fir = mid + 1;
			} else if (num < narr[mid]) {
				end = mid;
			} else {
				fir = mid + 1;
			}
		}
		return end;
	}

	static int searchLeft(int num) {
		int fir = 0;
		int end = N;
		while (true) {
			if (fir >= end)
				break;
			int mid = (fir + end) / 2;
			if (narr[mid] == num) {
				end = mid;
			} else if (num < narr[mid]) {
				end = mid;
			} else {
				fir = mid + 1;
			}
		}
		return fir + 1;
	}

}
