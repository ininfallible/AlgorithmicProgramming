import java.util.*;

public class test {
	public static void main(String[] args) {
		HashSet<String> a = new HashSet<>();
		a.add("a");
		a.add("b");
		a.add("c");
		HashSet<String> b = new HashSet<>();
		b.add("a");
		b.add("d");
		a.retainAll(b);
		System.out.println(a);
	}
}
