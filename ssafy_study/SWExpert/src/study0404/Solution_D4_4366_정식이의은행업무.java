package study0404;

import java.util.Scanner;

public class Solution_D4_4366_정식이의은행업무 {

	static int T;
	static String a, b;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			a = sc.next();
			b = sc.next();
			int alen = a.length();
			int blen = b.length();
			f: for (int i = 0; i < alen; i++) {
				char[] na = a.toCharArray();
				na[i] = (na[i] == '1' ? '0' : '1');
				for (int j = 0; j < blen; j++) {
					for (int k = 0; k < 3; k++) {
						char[] nb = b.toCharArray();
						if (nb[j] - '0' == k)
							continue;
						nb[j] = (char) (k + '0');
						if (Integer.parseInt(String.valueOf(na), 2) == Integer.parseInt(String.valueOf(nb), 3)) {
							System.out.println("#" + t + " " + Integer.parseInt(String.valueOf(na), 2));
							break f;
						}
					}
				}
			}
		}

	}
}
