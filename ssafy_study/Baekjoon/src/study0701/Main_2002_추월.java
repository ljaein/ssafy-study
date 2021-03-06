package study0701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_2002_추월 {

	static int N, ans;
	static boolean[] out;
	static Map<String, Integer> map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		out = new boolean[N+1];
		map = new HashMap<String, Integer>();
		int num = 1;
		for(int i=0;i<N;i++){
			map.put(br.readLine(), num++);
		}
		int sel = 1;
		for (int i = 0; i < N; i++) {
			int cur = map.get(br.readLine());
			if(cur>sel){
				ans++;
				out[cur]=true;
			}else{
				out[cur]=true;
				while(out[sel]){
					sel++;
					if(sel>N)
						break;
				}
			}
		}
		System.out.println(ans);
	}

}
