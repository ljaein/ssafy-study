package study0502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_3020_개똥벌레 {

	static int N, H;
	static int[] s, j;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		s = new int[H];
		j = new int[H];

		for (int i = 0; i < N; i++) {
			int len = Integer.parseInt(br.readLine());
			if (i % 2 == 0)
				s[len - 1]++;
			else
				j[len - 1]++;
		}

		for (int i = H - 2; i >= 0; i--) {
			s[i] += s[i + 1];
			j[i] += j[i + 1];
		}
		int min = Integer.MAX_VALUE;
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			int sum = s[i] + j[H - 1 - i];
			if (min > sum) {
				min = sum;
				cnt =1;
			}else if(min==sum)
				cnt++;
		}
		System.out.println(min + " " + cnt);
	}

}
