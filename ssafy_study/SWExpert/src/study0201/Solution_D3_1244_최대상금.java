package study0201;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_D3_1244_�ִ��� {

	static int T, N, cnt;
	static int[] num;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			String[] s = sc.next().split("");
			num = new int[s.length];
			N = sc.nextInt();
			for (int i = 0; i < s.length; i++) {
				num[i] = Integer.parseInt(s[i]);
			}
			int len = num.length;
			int idx = 0;
			while (idx < len - 1) {
				int max = 0;
				int midx = 0;
				for (int i = idx; i < len; i++) {
					if (max < num[i]) {
						max = num[i];
						midx = i;
					}
				}
				if (num[idx] != max) {
					swap(idx, midx);
					cnt++;
				}
				idx++;
			}
			for(int i=0;i<N-cnt;i++){
				swap(len-1,len-2);
			}
			System.out.println(Arrays.toString(num));

		}
	}

	public static void swap(int x, int y) {
		int temp = num[x];
		num[x] = num[y];
		num[y] = temp;
	}

}

//10
//123 1
//2737 1
//757148 1
//78466 2
//32888 2
//777770 5
//436659 2
//431159 7
//112233 3
//456789 10
//
//#1 321
//#2 7732
//#3 857147
//#4 87664
//#5 88832
//#6 777770
//#7 966354
//#8 954311
//#9 332211
//#10 987645
