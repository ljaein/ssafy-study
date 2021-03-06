package study0601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1972_놀라운문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s = br.readLine();
			boolean surp = true;
			if (s.equals("*"))
				break;
			int len = s.length();
			int n = 1;
			for (int j = 0; j <= len - 2; j++) {
				HashSet<String> set = new HashSet<>();
				int cnt = 0;
				for (int i = 0; i < len - n; i++) {
					String make = s.substring(i, i + 1) + s.substring(i + n, i + n + 1);
					set.add(make);
					cnt ++;
				}
				if(set.size()!= cnt){
					surp = false;
					break;
				}
				n++;
			}
			if(surp)
				System.out.println(s + " is surprising.");
			else
				System.out.println(s + " is NOT surprising.");
				
		}

	}

}
