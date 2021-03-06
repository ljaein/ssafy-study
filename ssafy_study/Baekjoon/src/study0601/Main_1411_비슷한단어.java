package study0601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1411_비슷한단어 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] res = new String[N];
		int ans =0;
		for(int i=0;i<N;i++){
			res[i] = "";
			String s = br.readLine();
			int[] alph = new int[26];
			int cnt = 1;
			for(int j=0;j<s.length();j++){
				if(alph[s.charAt(j)-'a']==0){
					alph[s.charAt(j)-'a']=cnt++;
				}
				res[i] += alph[s.charAt(j)-'a']+"";
			}
		}
		for(int i=0;i<N-1;i++){
			for(int j=i+1;j<N;j++){
				if(res[i].equals(res[j]))
					ans++;
			}
		}
        System.out.print(ans);

	}

}
