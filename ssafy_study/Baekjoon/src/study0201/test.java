package study0201;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class test {

	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		list.add(12);
		list.add(24);
		System.out.println(list.remove(0));
		System.out.println(list.toString());

	}

	static class Mal {
		int y, x;

		public Mal(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
