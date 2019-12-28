//implementation of bubblesort
public class Bubble {
	public static void main(String[] args) {
		int[] a  = new int[]{5, 4, 3, 2, 1};
		int temp = 0;
		for (int i = a.length - 1; i >= 0; i--) 
		for (int j = 0; j < i; j++) {
			if (a[j] > a[j+1]) {
				temp = a[j];
				a[j] = a[j+1];
				a[j+1] = temp;
			}
		}
		for (int i : a) System.out.println(i);
	}

}
