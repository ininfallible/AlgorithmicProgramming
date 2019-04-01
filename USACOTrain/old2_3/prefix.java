package old2_3;
/*
 ID: derekl3
 LANG: JAVA
 PROG: prefix
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author antonio081014
 * @time: 2012-5-7, 15:51:13
 * 
 *        Solution: length[i] represents the longest prefix of the first i
 *        characters; Check if a substring is the same with a prefix in dict,
 *        then check if the prefix before this length exists.
 * 
 *        PS:The only problem I have is for mutual String object, should use
 *        stringbuilder, which is much faster;
 */

public class prefix {

    List<String> dict;

    public static void main(String[] args) throws Exception {
	prefix main = new prefix();
	main.run();
	System.exit(0);
    }

    public void run() throws Exception {
	BufferedReader br = new BufferedReader(new FileReader("prefix.in"));
	BufferedWriter out = new BufferedWriter(new FileWriter("prefix.out"));

	dict = new ArrayList<String>();
	String str;
	while ((str = br.readLine()).compareTo(".") != 0) {
	    String[] strs = str.split("\\s");
	    for (int i = 0; i < strs.length; i++)
		dict.add(strs[i]);
	}

	StringBuilder sb = new StringBuilder();
	while ((str = br.readLine()) != null)
	    sb.append(str);

	String s = sb.toString();
	int[] length = new int[s.length() + 1];
	Arrays.fill(length, -1);
	length[0] = 0;
	int max = 0;
	for (int i = 1; i <= s.length(); i++) { //i is current pos in input
	    for (int j = 0; j < dict.size(); j++) {
		String tmp = dict.get(j); //tmp is prefix, cmp is substring of s 
		int len = tmp.length();
		if (len <= i) {
			
		    String cmp = s.substring(i - len, i);
		    // Key Part;
		    if (cmp.compareTo(tmp) == 0 && length[i - len] != -1) {
		    	length[i] = Math.max(length[i], len + length[i - len]); //if it's valid, length[i] is increased by length of prefix
		    }
		}
	    }
	    max = Math.max(max, length[i]);
	}

	// System.out.println(max);
	// System.out.println(length[string.length()]);
	br.close();
	out.write(Integer.toString(max) + "\n");
	out.close();
    }
}