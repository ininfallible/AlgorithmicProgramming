import java.io.*;
import java.util.*;

public class handles {
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(System.in);
		//Scanner in = new Scanner(new File("handles.in"));
		TreeMap<String, String> NtoO = new TreeMap<>();
		//TreeMap<String, String> OtoN = new TreeMap<>();
		final int N = in.nextInt();
		in.nextLine();
		for (int i = 0; i < N; i++) {
			StringTokenizer sT = new StringTokenizer(in.nextLine());
			String o = sT.nextToken();
			String n = sT.nextToken();
			if (!NtoO.containsKey(o)) {
				NtoO.put(n, o);
			} else {
				NtoO.put(n, NtoO.get(o));
				NtoO.remove(o);
			}
		}
		System.out.println(NtoO.keySet().size());
		for (String s : NtoO.keySet())
			System.out.println(NtoO.get(s) + " " + s);
	}
}
